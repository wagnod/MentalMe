package com.wagnod.core_ui.view_model

import androidx.lifecycle.ViewModel

abstract class ViewModelMapper<VM: ViewModel> {
    lateinit var viewModel: VM
}