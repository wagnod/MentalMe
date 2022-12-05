package com.wagnod.domain

import kotlinx.coroutines.flow.Flow

interface FirebaseDatabaseRepository {
    fun getUserData(): Flow<UserInfo?>
}