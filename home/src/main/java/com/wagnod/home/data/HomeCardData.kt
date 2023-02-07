package com.wagnod.home.data

import com.wagnod.home.R
import com.wagnod.home.data.HomeCardType.*

data class HomeCardData(
    val icon: Int,
    val name: String,
    val text: String,
    val type: HomeCardType
)

enum class HomeCardType {
    TRACKER, DIARY, GOALS
}

internal fun getCardsData() = listOf(
    HomeCardData(
        icon = R.drawable.ic_smile,
        name = "Трекер настроения",
        text = "Отмечай настроение каждый день и следи, как оно меняется!",
        type = TRACKER
    ),
    HomeCardData(
        icon = R.drawable.ic_diary,
        name = "Дневник",
        text = "Отвечай на вопросы и записывай свои мысли",
        type = DIARY
    ),
    HomeCardData(
        icon = R.drawable.ic_goals,
        name = "Цели",
        text = "Строй цели и отмечай их выполнение",
        type = GOALS
    )
)