import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
}

dependencies{
    implementation("com.android.tools.build:gradle:8.0.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.21")
}