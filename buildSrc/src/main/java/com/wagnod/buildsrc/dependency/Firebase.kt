package com.wagnod.buildsrc.dependency

import com.wagnod.buildsrc.dependency.Firebase.Version.AUTH_VERSION
import com.wagnod.buildsrc.dependency.Firebase.Version.DATABASE_VERSION

object Firebase {

    const val AUTH = "com.google.firebase:firebase-auth-ktx:$AUTH_VERSION"
    const val DATABASE = "com.google.firebase:firebase-database-ktx:$DATABASE_VERSION"

    private object Version {
        const val AUTH_VERSION = "21.1.0"
        const val DATABASE_VERSION = "20.1.0"
    }
}