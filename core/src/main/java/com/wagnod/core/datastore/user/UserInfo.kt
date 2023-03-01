package com.wagnod.core.datastore.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    @SerialName("id")
    val id: String = "",
    @SerialName("userImage")
    val userImage: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("status")
    val status: String = "",
    @SerialName("goals")
    val goals: List<Goal> = listOf()
)

fun UserInfo.toMap() = mapOf(
    "id" to id,
    "userImage" to userImage,
    "name" to name,
    "status" to status,
    "goals" to goals
)
