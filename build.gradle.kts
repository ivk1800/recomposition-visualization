plugins {
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.hotReload).apply(false)
    alias(libs.plugins.detekt.gradle)
    alias(libs.plugins.kotlin.serialization).apply(false)
}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")

    detekt {
        config.setFrom("$rootDir/detekt.yml")
        source.setFrom(
            files(
                "src/commonMain/kotlin",
                "src/jvmMain/kotlin",
                "src/jsMain/kotlin",
                "src/main/kotlin",
            ),
        )
    }

    dependencies {
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.7")
    }
}
