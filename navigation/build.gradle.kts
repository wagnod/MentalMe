plugins {
    id("libs.ui")
}

android {
    namespace = "com.wagnod.navigation"
}

dependencies {
    implementation(project(mapOf("path" to ":friends")))
    implementation(project(mapOf("path" to ":profile")))
    implementation(project(mapOf("path" to ":search")))
    implementation(project(mapOf("path" to ":home")))
    implementation(project(mapOf("path" to ":entries")))
    implementation(project(mapOf("path" to ":core_ui")))
    implementation(project(mapOf("path" to ":login")))
    implementation(project(mapOf("path" to ":dashboard")))
}
