plugins {
    id("libs.ui")
}

android {
    namespace = "com.wagnod.data"
}

dependencies {
    implementation(project(mapOf("path" to ":domain")))
    implementation(project(mapOf("path" to ":core_ui")))
}