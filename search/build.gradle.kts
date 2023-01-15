plugins {
    id("libs.ui")
}

android {
    namespace = "com.wagnod.search"
}

dependencies {
    implementation(project(mapOf("path" to ":core_ui")))
    implementation(project(mapOf("path" to ":data")))
    implementation(project(mapOf("path" to ":domain")))
}