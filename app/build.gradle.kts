import com.wagnod.buildsrc.dependency.BuildConfig
import com.wagnod.buildsrc.extensions.*

plugins {
    id("kotlin-android")
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = BuildConfig.APP_ID
    compileSdk = BuildConfig.COMPILE_SDK

    defaultConfig {
        applicationId = BuildConfig.APP_ID
        minSdk = BuildConfig.MIN_SDK
        targetSdk = BuildConfig.TARGET_SDK
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = BuildConfig.COMPOSE_VERSION
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(mapOf("path" to ":core_ui")))
    implementation(project(mapOf("path" to ":login")))
    implementation(project(mapOf("path" to ":navigation")))
    implementation(project(mapOf("path" to ":profile")))
    implementation(project(mapOf("path" to ":domain")))
    implementation(project(mapOf("path" to ":data")))
    implementation(project(mapOf("path" to ":home")))
    implementation(project(mapOf("path" to ":dashboard")))

    koinDependencies()
    navigationDependencies()
    commonUiDependencies()
    accompanistDependencies()
    unitTestDependencies()
    firebaseDependencies()
}