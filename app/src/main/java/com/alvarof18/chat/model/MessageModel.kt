package com.alvarof18.chat.model

import androidx.annotation.DrawableRes

data class MessageModel(
    val message: String,
    @DrawableRes val photoProfile: Int,
    val name: String,
    val timeMessage: String,
    val id:Int
)
