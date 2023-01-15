package com.wagnod.buildsrc.dependency

import com.wagnod.buildsrc.dependency.Basic.Version.COIL_VERSION
import com.wagnod.buildsrc.dependency.Basic.Version.COMPOSE
import com.wagnod.buildsrc.dependency.Basic.Version.COMPOSE_ACTIVITY
import com.wagnod.buildsrc.dependency.Basic.Version.CONSTRAINT
import com.wagnod.buildsrc.dependency.Basic.Version.CUSTOM_TABS_VERSION
import com.wagnod.buildsrc.dependency.Basic.Version.DATE_TIME_VERSION
import com.wagnod.buildsrc.dependency.Basic.Version.JODA
import com.wagnod.buildsrc.dependency.Basic.Version.KOTLIN_KTX
import com.wagnod.buildsrc.dependency.Basic.Version.LIFECYCLE
import com.wagnod.buildsrc.dependency.Basic.Version.SPLASH_SCREEN_VERSION
import com.wagnod.buildsrc.dependency.Basic.Version.TIMBER

object Basic {

    const val KOTLIN_CORE_KTX = "androidx.core:core-ktx:$KOTLIN_KTX"
    const val LIFECYCLE_LIB = "androidx.lifecycle:lifecycle-runtime-ktx:$LIFECYCLE"

    const val UI = "androidx.compose.ui:ui:$COMPOSE"
    const val MATERIAL = "androidx.compose.material:material:$COMPOSE"
    const val MATERIAL_ICONS_EXTENDED = "androidx.compose.material:material-icons-extended:$COMPOSE"
    const val TOOLING = "androidx.compose.ui:ui-tooling:$COMPOSE"
    const val TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:$COMPOSE"

    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:$COMPOSE_ACTIVITY"

    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout-compose:$CONSTRAINT"

    const val COIL = "com.github.skydoves:landscapist-coil:$COIL_VERSION"

    const val TIMBER_LIB = "com.jakewharton.timber:timber:$TIMBER"

    const val JODA_TIME = "joda-time:joda-time:$JODA"

    const val CUSTOM_TABS = "androidx.browser:browser:$CUSTOM_TABS_VERSION"

    const val DATE_TIME_DIALOGS = "io.github.vanpra.compose-material-dialogs:datetime:$DATE_TIME_VERSION"

    const val SPLASH_SCREEN = "androidx.core:core-splashscreen:$SPLASH_SCREEN_VERSION"

    object Version {
        const val COMPOSE = "1.4.0-alpha02"
        const val CONSTRAINT = "1.1.0-alpha01"
        const val KOTLIN_KTX = "1.9.0"
        const val TIMBER = "5.0.1"
        const val LIFECYCLE = "2.5.1"
        const val COMPOSE_ACTIVITY = "1.7.0-alpha02"
        const val COIL_VERSION = "2.1.0"
        const val JODA = "2.12.2"
        const val CUSTOM_TABS_VERSION = "1.4.0"
        const val DATE_TIME_VERSION = "0.9.0"
        const val SPLASH_SCREEN_VERSION = "1.0.0-beta01"
    }
}