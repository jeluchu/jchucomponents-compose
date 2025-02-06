plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin)
    alias(libs.plugins.jetbrains.dokka)
    alias(libs.plugins.compose.compiler)
    id("maven-publish")
}

android {
    namespace = "com.jeluchu.jchucomponents.ui"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
        targetSdk = 35
        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
        )
    }

    buildFeatures.compose = true

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
    implementation(libs.bundles.ktor)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose.bom)
    implementation(libs.bundles.ui.androidx)
    implementation(project(":jchucomponents-ktx"))
    debugImplementation(libs.androidx.compose.ui.ui.tooling.preview)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.jeluchu"
            artifactId = "jchucomponents-ui"
            version = libs.versions.jchucomponents.get()

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}