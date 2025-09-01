package ru.ivk1800

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.IrFileEntry
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.expressions.IrBlockBody
import org.jetbrains.kotlin.ir.expressions.IrFunctionExpression
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl
import org.jetbrains.kotlin.ir.expressions.impl.fromSymbolOwner
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.ir.util.fileEntry
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.ir.visitors.IrVisitor
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

class HighlightInjector(
    private val context: IrPluginContext,
) : IrVisitor<Unit, HighlightInjector.Data>() {

    private val highlightedAnnotation = FqName("ru.ivk1800.Highlighted")

    private val highlightSymbol: IrSimpleFunctionSymbol =
        context.referenceFunctions(CallableId(FqName("ru.ivk1800"), Name.identifier("highlight"))).first()

    class Data(var irClass: IrClass? = null)

    override fun visitElement(element: IrElement, data: Data) {
        element.acceptChildren(this, data)
    }

    override fun visitClass(declaration: IrClass, data: Data) {
        if (declaration.annotations.hasAnnotation(highlightedAnnotation)) {
            data.irClass = declaration
            super.visitClass(declaration, data)
        }
    }

    override fun visitSimpleFunction(declaration: IrSimpleFunction, data: Data) {
        val irClass = data.irClass ?: return
        val body: IrBlockBody = declaration.body as? IrBlockBody ?: return
        injectHighlightCall(fileEntry = irClass.fileEntry, body = body)
        super.visitSimpleFunction(declaration, data)
    }

    override fun visitFunctionExpression(expression: IrFunctionExpression, data: Data) {
        val irClass = data.irClass ?: return
        val body = expression.function.body as? IrBlockBody ?: return
        injectHighlightCall(fileEntry = irClass.fileEntry, body = body)
        super.visitFunctionExpression(expression, data)
    }

    private fun injectHighlightCall(fileEntry: IrFileEntry, body: IrBlockBody) {
        val bodySourceRangeInfo = fileEntry.getSourceRangeInfo(
            beginOffset = body.startOffset,
            endOffset = body.endOffset,
        )

        val highlightCall = IrCallImpl.fromSymbolOwner(
            startOffset = UNDEFINED_OFFSET,
            endOffset = UNDEFINED_OFFSET,
            type = context.irBuiltIns.unitType,
            symbol = highlightSymbol,
        )
        highlightCall.arguments[0] = IrConstImpl.int(
            startOffset = UNDEFINED_OFFSET,
            endOffset = UNDEFINED_OFFSET,
            type = context.irBuiltIns.intType,
            value = bodySourceRangeInfo.startOffset,
        )
        highlightCall.arguments[1] = IrConstImpl.int(
            startOffset = UNDEFINED_OFFSET,
            endOffset = UNDEFINED_OFFSET,
            type = context.irBuiltIns.intType,
            value = bodySourceRangeInfo.endOffset,
        )
        body.statements.add(0, highlightCall)
    }
}
