package com.mertkaragul.noteappcleanarchitecture.Domain.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.UserModelDto


@Entity
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name: String,
    val surname: String,
    val image: String,
    val createTime : String,
    val updateTime :String
)

fun UserModel.toUserModelDto() : UserModelDto{
    return UserModelDto(this.name,this.surname,this.image,this.createTime,this.updateTime)

}