package ru.ivk1800

import org.jetbrains.kotlin.GeneratedDeclarationKey
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.backend.common.lower.DeclarationIrBuilder
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.builders.irCall
import org.jetbrains.kotlin.ir.builders.irString
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin.GeneratedByPlugin
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.ir.visitors.IrVisitor
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import java.io.File

class SourceCodeInjector(
    private val context: IrPluginContext,
) : IrVisitor<Unit, SourceCodeInjector.Data>() {

    private val highlightedAnnotationName = FqName("ru.ivk1800.Highlighted")

    class Data(
        var sourceCode: String? = null,
    )

    fun interestedIn(key: GeneratedDeclarationKey?): Boolean = key == HighlightedFirExtension.Key

    override fun visitElement(element: IrElement, data: Data) {
        element.acceptChildren(this, data)
    }

    override fun visitClass(declaration: IrClass, data: Data) {
        if (declaration.annotations.hasAnnotation(highlightedAnnotationName)) {
            val parent = declaration.parent as IrFile
            val sourceRangeInfo = parent.fileEntry.getSourceRangeInfo(
                beginOffset = declaration.startOffset,
                endOffset = declaration.endOffset,
            )

            val lines = File(sourceRangeInfo.filePath).readLines().joinToString("\n")

            data.sourceCode = lines
            super.visitClass(declaration, data = data)
        }
    }

    override fun visitSimpleFunction(declaration: IrSimpleFunction, data: Data) {
        val origin = declaration.origin
        if (origin !is GeneratedByPlugin || !interestedIn(origin.pluginKey)) {
            super.visitSimpleFunction(declaration, data)
            return
        }

        val sourceCode = data.sourceCode
        if (sourceCode != null) {
            val callableId = CallableId(
                packageName = FqName("ru.ivk1800"),
                callableName = Name.identifier("HighlightedText"),
            )

            val highlightedTextSymbol = context.referenceFunctions(callableId).first()

            val builder = DeclarationIrBuilder(context, declaration.symbol)

            val irCall = builder.irCall(highlightedTextSymbol)
            irCall.arguments[0] = builder.irString(sourceCode)

            val body = context.irFactory.createBlockBody(
                startOffset = UNDEFINED_OFFSET,
                endOffset = UNDEFINED_OFFSET,
            )

            body.statements += irCall
            declaration.body = body
        }

        super.visitSimpleFunction(declaration, data)
    }
}
