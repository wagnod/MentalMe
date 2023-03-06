package com.wagnod.data.firebase

import com.google.firebase.database.FirebaseDatabase
import com.wagnod.domain.firebase.FirebaseDatabaseReferences

class FirebaseDatabaseReferencesImpl(
    private val database: FirebaseDatabase
) : FirebaseDatabaseReferences {

    override suspend fun getUserReference(id: String) =
        database.getReference(USER_REFERENCE).child(id)

    override suspend fun getTodaySelectionReference() =
        database.getReference(TODAY_SELECTION_REFERENCE)

    override suspend fun getArticlesReference() =
        database.getReference(ARTICLES_REFERENCE)

    override suspend fun getDailiesReference() =
        database.getReference(DAILIES_REFERENCE)

    private companion object {
        const val USER_REFERENCE = "users"
        const val TODAY_SELECTION_REFERENCE = "today_selection"
        const val ARTICLES_REFERENCE = "articles"
        const val DAILIES_REFERENCE = "dailies"
    }
}