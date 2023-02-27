package com.wagnod.core_ui.di

import com.wagnod.core_ui.sheet.ShowBottomSheetHelper
import org.koin.dsl.module

val coreUIModule = module {
    single { ShowBottomSheetHelper() }
}