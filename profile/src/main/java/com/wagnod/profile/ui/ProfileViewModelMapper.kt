package com.wagnod.profile.ui

import com.wagnod.core_ui.view_model.ViewModelMapper
import com.wagnod.domain.UserInfo
import com.wagnod.profile.data.User

class ProfileViewModelMapper : ViewModelMapper<ProfileViewModel>() {

    fun mapUserInfoToUser(userInfo: UserInfo): User = User(
        id = userInfo.id,
        profileImage = userInfo.userImage,
        name = userInfo.name,
        status = userInfo.status
    )
}