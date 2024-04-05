/*
 *
 *  Copyright 2022 Jeluchu
 *
 */

plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {

    compileSdk = 34
    defaultConfig {
        applicationId = "com.jeluchu.composer"
        minSdk = 22
        targetSdk = 34
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
    composeOptions.kotlinCompilerExtensionVersion = "1.5.8"
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