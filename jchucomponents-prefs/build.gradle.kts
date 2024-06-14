plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin)
    alias(libs.plugins.jetbrains.dokka)
    alias(libs.plugins.compose.compiler)
    id("maven-publish")
}

android {
    namespace = "com.jeluchu.jchucomponents.prefs"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34
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
    implementation(libs.bundles.preferences.androidx)
    implementation(libs.bundles.preferences.google)
    implementation(libs.bundles.preferences.compose)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.jeluchu"
            artifactId = "jchucomponents-prefs"
            version = libs.versions.jchucomponents.get()

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}