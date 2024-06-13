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
include(
        ":jchucomponents-core",
        ":jchucomponents-ui",
        ":jchucomponents-ktx",
        ":jchucomponents-qr",
        ":jchucomponents-pay",
        ":jchucomponents-prefs",
        "app"
)

