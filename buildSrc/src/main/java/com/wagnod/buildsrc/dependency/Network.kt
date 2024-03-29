package com.wagnod.buildsrc.dependency

import com.wagnod.buildsrc.dependency.Network.Version.GSON
import com.wagnod.buildsrc.dependency.Network.Version.GSON_CONVERTER
import com.wagnod.buildsrc.dependency.Network.Version.KOTLINX_SERIALIZATION
import com.wagnod.buildsrc.dependency.Network.Version.OKHTTP

object Network {

    const val OKHTTP_LIB = "com.squareup.okhttp3:okhttp:$OKHTTP"
    const val OKHTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:$OKHTTP"

    const val KOTLIN_SERIALIZATION = "org.jetbrains.kotlinx:kotlinx-serialization-json:$KOTLINX_SERIALIZATION"

    const val GSON_LIB = "com.google.code.gson:gson:$GSON"
    const val GSON_CONVERTER_LIB = "com.squareup.retrofit2:converter-gson:$GSON_CONVERTER"

    private object Version {
        const val OKHTTP = "4.10.0"
        const val KOTLINX_SERIALIZATION = "1.4.1"
        const val GSON = "2.10"
        const val GSON_CONVERTER = "2.9.0"
    }
}