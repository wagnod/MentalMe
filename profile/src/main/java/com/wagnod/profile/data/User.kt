package com.wagnod.profile.data

data class User(
    val id: String,
    val profileImage: String,
    val name: String,
    val status: String
) {
    companion object {
        fun getSampleData() = User(
            id = "1234567890",
            profileImage = "https://i.natgeofe.com/n/3861de2a-04e6-45fd-aec8-02e7809f9d4e/02-cat-training-NationalGeographic_1484324_square.jpg",
            name = "Name",
            status = "Status"
        )
    }
}
