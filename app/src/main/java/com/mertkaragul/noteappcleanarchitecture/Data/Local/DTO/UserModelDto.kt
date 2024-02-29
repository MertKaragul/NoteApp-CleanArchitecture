package com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.UserModel

data class UserModelDto(
    val name: String?,
    val surname: String?,
    val image: String?,
    val createTime:String,
    val updateTime: String
)

