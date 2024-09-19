plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin)
    alias(libs.plugins.jetbrains.dokka)
    id("maven-publish")
}

android {
    namespace = "com.jeluchu.jchucomponents.core"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
        targetSdk = 35
        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
        )
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(libs.bundles.core.androidx)
    implementation(libs.bundles.core.squareup)
    implementation(libs.bundles.core.jetbrains)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.jeluchu"
            artifactId = "jchucomponents-core"
            version = libs.versions.jchucomponents.get()

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}