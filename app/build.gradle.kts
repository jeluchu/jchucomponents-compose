plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin)
    alias(libs.plugins.jetbrains.dokka)
    alias(libs.plugins.compose.compiler)
}

android {
    compileSdk = 35
    defaultConfig {
        applicationId = "com.jeluchu.composer"
        minSdk = 22
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    
    buildFeatures.compose = true
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    namespace = "com.jeluchu.composer"
}

dependencies {
    implementation(libs.bundles.ui.compose)
    implementation(libs.androidx.appcompat)
    implementation(project(":jchucomponents-core"))
    implementation(project(":jchucomponents-ui"))
    implementation(project(":jchucomponents-ktx"))
    implementation(project(":jchucomponents-qr"))
    implementation(project(":jchucomponents-pay"))
}