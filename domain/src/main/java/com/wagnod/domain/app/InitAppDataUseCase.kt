package com.wagnod.domain.app

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.wagnod.domain.AppDispatchers
import com.wagnod.domain.UseCase
import com.wagnod.domain.dashboard.usecase.SubscribeDailiesUseCase
import com.wagnod.domain.dashboard.usecase.SubscribeTodaySelectionUseCase
import com.wagnod.domain.execute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class InitAppDataUseCase(
    private val subscribeTodaySelectionUseCase: SubscribeTodaySelectionUseCase,
    private val subscribeDailiesUseCase: SubscribeDailiesUseCase,
    private val dispatchers: AppDispatchers
) : UseCase<Unit, Unit> {
    private val referencedListeners = mutableListOf<Pair<DatabaseReference, ValueEventListener>>()

    override suspend fun execute(input: Unit) {
        init()
    }

    private fun Pair<DatabaseReference, ValueEventListener>.add() = referencedListeners.add(this)

    private suspend fun init() = CoroutineScope(dispatchers.io).launch {
        runCatching {
            listOf(
                async { subscribeTodaySelectionUseCase.execute().add() },
                async { subscribeDailiesUseCase.execute().add() }
            ).awaitAll()
        }
    }

    fun clearSubscribed() = CoroutineScope(dispatchers.io).launch {
        referencedListeners.map { referenceToListener ->
            async {
                with(referenceToListener) { first.removeEventListener(second) }
            }
        }.awaitAll()
    }
}