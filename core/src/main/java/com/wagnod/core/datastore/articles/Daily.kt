package com.wagnod.core.datastore.articles

@kotlinx.serialization.Serializable
data class Daily(
    val image: String = "",
    val title: String = "",
    val description: String = ""
)
