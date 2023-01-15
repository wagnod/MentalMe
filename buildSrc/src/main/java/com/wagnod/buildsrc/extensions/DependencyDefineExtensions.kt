package com.wagnod.buildsrc.extensions

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.debugImplementation(dependencyNotation: String): Dependency? {
    return add("debugImplementation", dependencyNotation)
}

fun DependencyHandler.implementation(dependencyNotation: String): Dependency? {
    return add("implementation", dependencyNotation)
}

fun DependencyHandler.api(dependencyNotation: String): Dependency? {
    return add("api", dependencyNotation)
}

fun DependencyHandler.kapt(dependencyNotation: String): Dependency? {
    return add("kapt", dependencyNotation)
}

fun DependencyHandler.testImplementation(dependencyNotation: String): Dependency? {
    return add("testImplementation", dependencyNotation)
}


fun DependencyHandler.androidTestImplementation(dependencyNotation: String): Dependency? {
    return add("androidTestImplementation", dependencyNotation)
}