package com.wagnod.core_ui.sheet

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import com.wagnod.core_ui.sheet.data.BottomSheetParams
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

typealias Input = BottomSheetParams?

@OptIn(ExperimentalMaterialApi::class)
class ShowBottomSheetHelper {

    private lateinit var coroutineScope: CoroutineScope
    private lateinit var bottomSheetState: ModalBottomSheetState

    private val _showItems: Channel<Input> = Channel()
    val showItems = _showItems.receiveAsFlow()

    fun init(scope: CoroutineScope, state: ModalBottomSheetState) {
        coroutineScope = scope
        bottomSheetState = state
    }

    fun showItems(items: Input) = coroutineScope.launch {
        _showItems.send(items)
        bottomSheetState.show()
    }

    fun closeSheet() = coroutineScope.launch {
        bottomSheetState.hide()
        _showItems.send(null)
    }
}