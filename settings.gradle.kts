pluginManagement {
        repositories {
                google()
                mavenCentral()
                gradlePluginPortal()
                maven("https://jitpack.io")
        }
}
dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
                google()
                mavenCentral()
                gradlePluginPortal()
                maven("https://jitpack.io")
        }
}

rootProject.name = "JchuComponents"
include(":jchucomponents-core")
include(":jchucomponents-ui")
include(":jchucomponents-ktx")
include(":jchucomponents-qr")
include(":jchucomponents-pay")
include(":jchucomponents-prefs")
include("app")