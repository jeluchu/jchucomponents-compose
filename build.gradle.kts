import org.jetbrains.dokka.gradle.*

plugins {
    alias(libs.plugins.jetbrains.dokka) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin) apply false
    alias(libs.plugins.compose.compiler) apply false
}

tasks.withType<DokkaMultiModuleTask> {
    val currentVersion = libs.versions.jchucomponents.get()
    val previousVersionsDirectory = project.rootProject.projectDir.resolve("previousDocVersions").invariantSeparatorsPath

    val versioningPlugin = "org.jetbrains.dokka.versioning.VersioningPlugin"
    val configVersioning = """{ "version": "$currentVersion", "olderVersionsDir":"$previousVersionsDirectory" }"""

    val dokkaConfig = "org.jetbrains.dokka.base.DokkaBase"
    val configBase = """{ "customAssets": ${listOf(file("docs/assets/logo-icon.svg"))}, "footerMessage": "© 2024 - Jéluchu" }"""

    val mapOf = mapOf(versioningPlugin to configVersioning, dokkaConfig to configBase)
    outputDirectory.set(file(projectDir.toPath().resolve("docs/assets/logo-icon.svg").resolve(currentVersion)))
    pluginsMapConfiguration.set(mapOf)
}

