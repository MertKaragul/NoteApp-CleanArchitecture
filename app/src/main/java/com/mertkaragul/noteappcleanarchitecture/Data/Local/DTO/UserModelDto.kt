package com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.UserModel

@Entity
data class UserModelDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String?,
    val surname: String?,
    val image: String?,
    val createTime:String,
    val updateTime: String
)



fun UserModelDto.toUserModel() : UserModel{
    return UserModel(this.name ?: "", this.surname ?: "", this.image.toString())
}