package ru.ivk1800

import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar

class MyFirExtensionRegistrar : FirExtensionRegistrar() {
    override fun ExtensionRegistrarContext.configurePlugin() {
        +::HighlightedFirExtension
    }
}
