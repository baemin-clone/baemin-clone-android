package com.professionalandroid.apps.baemin_clone_android

data class email(
    val email: String
)

data class NewUserInfo(
    val email: String,
    val pwd: String,
    val nickname: String,
    val birth: String
)

data class UserIdSet(
    val email: String,
    val pwd: String
)

data class socailLoginToken(
    val accessToken: String
)

data class NewSocialUserInfo(
    val nickname: String,
    val birth: String,
    val accessToken: String
)