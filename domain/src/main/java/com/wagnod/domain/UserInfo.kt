package com.wagnod.domain

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
    val status: String = ""
)

fun UserInfo.toMap(): Map<String, Any?> {
    return mapOf(
        "id" to id,
        "userImage" to userImage,
        "name" to name,
        "status" to status,
    )
}
