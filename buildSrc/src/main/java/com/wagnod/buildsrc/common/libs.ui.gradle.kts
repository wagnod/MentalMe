import com.wagnod.buildsrc.extensions.accompanistDependencies
import com.wagnod.buildsrc.extensions.commonUiDependencies
import com.wagnod.buildsrc.extensions.navigationDependencies
import org.gradle.kotlin.dsl.dependencies
import com.wagnod.buildsrc.dependency.Basic.Version

plugins {
    id("libs.common")
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.COMPOSE
    }
}

dependencies {
    implementation(project(mapOf("path" to ":domain")))

    accompanistDependencies()
    commonUiDependencies()
    navigationDependencies()
}