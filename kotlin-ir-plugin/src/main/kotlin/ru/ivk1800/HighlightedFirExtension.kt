package ru.ivk1800

import org.jetbrains.kotlin.GeneratedDeclarationKey
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.expressions.builder.buildAnnotation
import org.jetbrains.kotlin.fir.expressions.impl.FirEmptyAnnotationArgumentMapping
import org.jetbrains.kotlin.fir.extensions.FirDeclarationGenerationExtension
import org.jetbrains.kotlin.fir.extensions.FirDeclarationPredicateRegistrar
import org.jetbrains.kotlin.fir.extensions.MemberGenerationContext
import org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate
import org.jetbrains.kotlin.fir.extensions.predicateBasedProvider
import org.jetbrains.kotlin.fir.plugin.createMemberFunction
import org.jetbrains.kotlin.fir.symbols.SymbolInternals
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol
import org.jetbrains.kotlin.fir.types.ConeTypeProjection
import org.jetbrains.kotlin.fir.types.builder.buildResolvedTypeRef
import org.jetbrains.kotlin.fir.types.constructClassType
import org.jetbrains.kotlin.fir.types.toLookupTag
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

class HighlightedFirExtension(session: FirSession) : FirDeclarationGenerationExtension(session) {

    companion object {
        private val HighlightedSourceCodeName = Name.identifier("HighlightedSourceCode")
        private val Predicate = LookupPredicate.create { annotated(FqName("ru.ivk1800.Highlighted")) }
    }

    private val matchedClasses by lazy {
        session.predicateBasedProvider.getSymbolsByPredicate(Predicate).filterIsInstance<FirRegularClassSymbol>()
    }

    @OptIn(SymbolInternals::class)
    override fun generateFunctions(
        callableId: CallableId,
        context: MemberGenerationContext?,
    ): List<FirNamedFunctionSymbol> {
        if (callableId.callableName != HighlightedSourceCodeName || context == null) return emptyList()

        val function = createMemberFunction(
            owner = context.owner,
            key = Key,
            name = callableId.callableName,
            returnType = session.builtinTypes.unitType.coneType,
        )
        function.replaceAnnotations(
            listOf(
                buildAnnotation {
                    annotationTypeRef = buildResolvedTypeRef {
                        val constructClassType =
                            ClassId(FqName("androidx.compose.runtime"), Name.identifier("Composable")).toLookupTag()
                                .constructClassType(typeArguments = ConeTypeProjection.EMPTY_ARRAY)
                        coneType = constructClassType
                    }
                    argumentMapping = FirEmptyAnnotationArgumentMapping
                },
            ),
        )
        return listOf(function.symbol)
    }

    override fun getCallableNamesForClass(classSymbol: FirClassSymbol<*>, context: MemberGenerationContext): Set<Name> {
        return when {
            classSymbol in matchedClasses -> setOf(HighlightedSourceCodeName)
            else -> emptySet()
        }
    }

    override fun FirDeclarationPredicateRegistrar.registerPredicates() {
        register(Predicate)
    }

    object Key : GeneratedDeclarationKey() {
        override fun toString(): String = "HighlightedSourceCodeKey"
    }
}
