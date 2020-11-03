package com.professionalandroid.apps.baemin_clone_android

data class email(
    var email: String
)

data class NewUserInfo(
    var email: String,
    var pwd: String,
    var nickname: String,
    var birth: String
)


data class UserIdSet(
    var email: String,
    var pwd: String
)

data class socailLoginToken(
    var accessToken: String
)