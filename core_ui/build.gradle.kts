plugins {
    id("libs.ui")
}

android {
    namespace = "com.wagnod.core_ui"
}

dependencies {
    implementation(project(mapOf("path" to ":data")))
}