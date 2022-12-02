package com.wagnod.core_ui.base

import androidx.lifecycle.ViewModel

abstract class ViewModelMapper<VM: ViewModel> {
    lateinit var viewModel: VM
}