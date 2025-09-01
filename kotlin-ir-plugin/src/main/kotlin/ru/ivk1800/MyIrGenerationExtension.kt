package ru.ivk1800

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment

class MyIrGenerationExtension : IrGenerationExtension {
    override fun generate(moduleFragment: IrModuleFragment, pluginContext: IrPluginContext) {
        moduleFragment.acceptChildren(HighlightInjector(pluginContext), HighlightInjector.Data())
        moduleFragment.acceptChildren(SourceCodeInjector(pluginContext), SourceCodeInjector.Data())
    }
}
