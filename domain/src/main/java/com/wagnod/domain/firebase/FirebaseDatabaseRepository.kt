package com.wagnod.domain.firebase

import com.wagnod.core.datastore.user.Goal
import com.wagnod.core.datastore.user.UserInfo
import kotlinx.coroutines.flow.Flow

interface FirebaseDatabaseRepository {
    suspend fun getUserData(): Flow<UserInfo?>
    suspend fun createGoal(goals: List<Goal>) : Boolean
}