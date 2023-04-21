package com.wagnod.domain.firebase

import com.google.firebase.database.DatabaseReference

interface FirebaseDatabaseReferences {
    suspend fun getUserReference(id: String): DatabaseReference
    suspend fun getTodaySelectionReference(): DatabaseReference
    suspend fun getArticlesReference(): DatabaseReference
    suspend fun getDailiesReference(): DatabaseReference
    suspend fun getCategoryReference(): DatabaseReference
}