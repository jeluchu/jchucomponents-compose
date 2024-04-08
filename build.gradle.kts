import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.dokka.versioning.VersioningConfiguration
import org.jetbrains.dokka.versioning.VersioningPlugin

/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.org.jetbrains.dokka)
        classpath(libs.org.jetbrains.dokka.versioning)
        classpath(libs.com.android.tools.build.gradle)
        classpath(libs.org.jetbrains.kotlin.kotlin.gradle.plugin)
    }
}

allprojects {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

plugins {
    id("org.jetbrains.dokka") version "1.9.20" apply true
}

val currentVersion = "2.0.0-alpha07"
val previousVersionsDirectory = project.rootProject.projectDir.resolve("previousDocVersions").invariantSeparatorsPath

tasks.dokkaHtmlMultiModule {
    pluginConfiguration<VersioningPlugin, VersioningConfiguration> {
        version = currentVersion
        olderVersionsDir = file(previousVersionsDirectory)
        moduleName.set("JchuComponents")
        outputDirectory.set(file("docs"))
    }
    pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
        customAssets = listOf(file("docs/assets/logo-icon.svg"))
        footerMessage = "© 2024 - Jéluchu"
    }
}