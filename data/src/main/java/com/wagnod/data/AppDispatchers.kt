package com.wagnod.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface AppDispatchers {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
}

data class AppDispatchersImpl(
    override val io: CoroutineDispatcher = Dispatchers.IO,
    override val main: CoroutineDispatcher = Dispatchers.Main,
    override val default: CoroutineDispatcher = Dispatchers.Default
): AppDispatchers

data class TestDispatchersImpl(
    override val io: CoroutineDispatcher = Dispatchers.Unconfined,
    override val main: CoroutineDispatcher = Dispatchers.Unconfined,
    override val default: CoroutineDispatcher = Dispatchers.Unconfined
): AppDispatchers