plugins {
    id("libs.ui")
}

android {
    namespace = "com.wagnod.data"
}

dependencies {
    implementation(project(mapOf("path" to ":domain")))
}