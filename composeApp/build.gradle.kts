import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.reload.gradle.ComposeHotRun
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeFeatureFlag

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)
    alias(libs.plugins.hotReload)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.dependencyGuard)
}

dependencyGuard {
    configuration("jsRuntimeClasspath")
    configuration("jsCompileClasspath")
}

kotlin {
    jvm()

    js {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.ktor.client.core)
            implementation(libs.navigation.compose)
            implementation(libs.kotlinx.serialization.json)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }

        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}

compose.resources {
    packageOfResClass = "ru.ivk1800.resources"
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Recomposition visualization"
            packageVersion = "1.0.0"

            linux {
                iconFile.set(project.file("desktopAppIcons/LinuxIcon.png"))
            }
            windows {
                iconFile.set(project.file("desktopAppIcons/WindowsIcon.ico"))
            }
            macOS {
                iconFile.set(project.file("desktopAppIcons/MacosIcon.icns"))
                bundleID = "ru.ivk1800.desktopApp"
            }
        }
    }
}

//https://github.com/JetBrains/compose-hot-reload
composeCompiler {
    featureFlags.add(ComposeFeatureFlag.OptimizeNonSkippingGroups)
}
tasks.withType<ComposeHotRun>().configureEach {
    mainClass.set("MainKt")
}

dependencies {
    org.jetbrains.kotlin.gradle.plugin.PLUGIN_CLASSPATH_CONFIGURATION_NAME(project(":kotlin-ir-plugin"))
}

tasks.register("buildApp") {
    dependsOn("jsBrowserDistribution")

    val distDir = layout.buildDirectory.dir("dist/js/productionExecutable")
    val buildTimestamp: String by extra { System.currentTimeMillis().toString() }

    doLast {
        val dist = distDir.get().asFile
        if (!dist.exists()) {
            throw GradleException("Distribution directory not found: $distDir")
        }

        val composeResourcesDir = File(dist, "composeResources")
        if (!composeResourcesDir.exists()) {
            throw GradleException("composeResources not found in $dist")
        }
        val newComposeResourcesDirName = "composeResources-${buildTimestamp}"
        composeResourcesDir.renameTo(File(dist, newComposeResourcesDirName))

        val targetDir = File(dist, buildTimestamp)
        targetDir.mkdirs()

        dist.listFiles()
            ?.filter {
                it.isFile && (it.name.startsWith("composeApp") || it.name.startsWith("skiko")) ||
                        it.name.endsWith(".wasm")
            }
            ?.forEach { file ->
                file.copyTo(File(targetDir, file.name), overwrite = true)
                file.delete()
            }

        val index = File(dist, "index.html")
        if (!index.exists()) {
            throw GradleException("index.html not found in $dist")
        }

        val updatedIndex = index.readText()
            .replace(
                """<script\s+src=["']composeApp\.js["']></script>""".toRegex(),
                """<script src="$buildTimestamp/composeApp.js"></script>""",
            )
            .replace(
                """<script\s+src=["']skiko\.js["']></script>""".toRegex(),
                """<script src="$buildTimestamp/skiko.js"></script>""",
            )

        index.writeText(updatedIndex)

        val composeAppFile = File(targetDir, "composeApp.js")
        if (!composeAppFile.exists()) {
            throw GradleException("composeApp.js not found in $dist")
        }

        val newComposeAppContent = composeAppFile.readText()
            .replace(
                "composeResources/ru.ivk1800.resources/",
                "$newComposeResourcesDirName/ru.ivk1800.resources/",
            )
        composeAppFile.writeText(newComposeAppContent)
    }
}
