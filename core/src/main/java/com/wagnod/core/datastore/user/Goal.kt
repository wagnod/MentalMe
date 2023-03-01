package com.wagnod.core.datastore.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Goal(
    @SerialName("goal_name")
    val name: String = "",
    @SerialName("goal_description")
    val description: String = "",
    @SerialName("goal_is_checked")
    val checked: Boolean = false
)