package com.wagnod.mentalme.ui.data

import com.wagnod.mentalme.R
import com.wagnod.mentalme.navigation.Navigator
import com.wagnod.mentalme.navigation.home.HomeNavigator

data class HomeCardData(
    val icon: Int,
    val name: String,
    val text: String,
    val onclick: () -> Unit
)

internal fun getCardsData(navigator: Navigator) = listOf(
    HomeCardData(
        icon = R.drawable.ic_smile,
        name = "Трекер настроения",
        text = "Отмечай настроение каждый день и следи, как оно меняется!",
        onclick = {
            navigator.homeNavigator.navigateToTracker()
        }
    ),
    HomeCardData(
        icon = R.drawable.ic_diary,
        name = "Дневник",
        text = "Отвечай на вопросы и записывай свои мысли",
        onclick = {
            navigator.homeNavigator.navigateToDiary()
        }
    ),
    HomeCardData(
        icon = R.drawable.ic_goals,
        name = "Цели",
        text = "Строй цели и отмечай их выполнение",
        onclick = {
            navigator.homeNavigator.navigateToGoals()
        }
    )
)
