plugins {
    id("libs.ui")
}

android {
    namespace = "com.wagnod.profile"
}

dependencies {
    implementation(project(mapOf("path" to ":core_ui")))
    implementation(project(mapOf("path" to ":data")))
    implementation(project(mapOf("path" to ":domain")))
    implementation(project(mapOf("path" to ":core")))
}