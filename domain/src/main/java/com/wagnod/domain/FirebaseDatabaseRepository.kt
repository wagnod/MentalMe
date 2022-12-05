package com.wagnod.domain

import kotlinx.coroutines.flow.Flow

interface FirebaseDatabaseRepository {
    suspend fun getUserData(): Flow<UserInfo?>
    suspend fun createGoal(goals: List<Goal>) : Boolean
}