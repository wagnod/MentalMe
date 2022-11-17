package com.wagnod.mentalme.ui.home.goals.data

data class GoalItem(
    val title: String,
    val description: String,
    val isDone: Boolean
)

internal fun getGoals() = listOf(
    GoalItem("Дизайн", "Начать работать над дизайном приложения", true),
    GoalItem("Дизайн", "Начать работать над дизайном приложения", true),
    GoalItem("Дизайн", "Начать работать над дизайном приложения", true),
    GoalItem("Дизайн", "Начать работать над дизайном приложения", true),
    GoalItem("Дизайн", "Начать работать над дизайном приложения", true),
    GoalItem("Дизайн", "Начать работать над дизайном приложения", true),
    GoalItem("Дизайн", "Начать работать над дизайном приложения", true),
    GoalItem("Дизайн", "Начать работать над дизайном приложения", true),
    GoalItem("Дизайн", "Начать работать над дизайном приложения", true),
    GoalItem("Дизайн", "Начать работать над дизайном приложения", true),
    GoalItem("Графики", "Продумать реализацию отображения графиков", false)
)