package com.wagnod.buildsrc.extensions

import com.wagnod.buildsrc.dependency.*
import org.gradle.api.artifacts.dsl.DependencyHandler


fun DependencyHandler.firebaseDependencies() {
    implementation(Firebase.AUTH)
    implementation(Firebase.DATABASE)
}

fun DependencyHandler.koinDependencies() {
    implementation(Koin.ANDROID)
    implementation(Koin.COMPOSE)
}

fun DependencyHandler.navigationDependencies() {
    implementation(Navigation.COMPOSE)
    implementation(Navigation.COMMON)
}

fun DependencyHandler.commonUiDependencies() {
    implementation(Basic.KOTLIN_CORE_KTX)
    implementation(Basic.LIFECYCLE_LIB)
    implementation(Basic.UI)
    implementation(Basic.MATERIAL)
    implementation(Basic.MATERIAL_ICONS_EXTENDED)
    implementation(Basic.TOOLING)
    implementation(Basic.TOOLING_PREVIEW)
    implementation(Basic.CONSTRAINT_LAYOUT)
    implementation(Basic.TIMBER_LIB)
    implementation(Basic.ACTIVITY_COMPOSE)
    implementation(Basic.COIL)
    implementation(Basic.CUSTOM_TABS)
    implementation(Basic.SPLASH_SCREEN)
}


fun DependencyHandler.networkDependencies() {
    implementation(Network.OKHTTP_LIB)
    implementation(Network.OKHTTP_LOGGING)
    implementation(Network.KOTLIN_SERIALIZATION)
    implementation(Network.GSON_LIB)
    implementation(Network.GSON_CONVERTER_LIB)
}


fun DependencyHandler.accompanistDependencies() {
    implementation(Accompanist.PAGER)
    implementation(Accompanist.INSETS)
    implementation(Accompanist.FLOW_ROW)
    implementation(Accompanist.SYSTEM_UI)
    implementation(Accompanist.SWIPE_REFRESH)
    implementation(Accompanist.MATERIAL_PLACEHOLDER)
}

fun DependencyHandler.dateTimeDependencies() {
    implementation(Basic.JODA_TIME)
    implementation(Basic.DATE_TIME_DIALOGS)
}

fun DependencyHandler.unitTestDependencies() {
    testImplementation(UnitTest.JUNIT_LIB)
    testImplementation(UnitTest.JUNIT_JB)
    testImplementation(UnitTest.JUNIT_RUNTIME)
    testImplementation(UnitTest.JUNIT_PARAMETRIZED)
    testImplementation(UnitTest.MOCKITO_LIB)
    testImplementation(UnitTest.MOCKITO_CORE)
    testImplementation(UnitTest.MOCKITO_INLINE_LIB)
    testImplementation(UnitTest.MOCK_WEB_SERVER_LIB)
    testImplementation(UnitTest.COROUTINES_LIB)
}