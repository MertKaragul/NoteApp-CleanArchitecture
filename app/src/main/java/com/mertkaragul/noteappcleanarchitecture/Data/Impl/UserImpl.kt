package com.mertkaragul.noteappcleanarchitecture.Data.Impl

import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.UserModelDto
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.toUserModel
import com.mertkaragul.noteappcleanarchitecture.Data.Local.Daos.UserDao
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.UserModel
import com.mertkaragul.noteappcleanarchitecture.Domain.Repo.IUserRepo
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class UserImpl @Inject constructor(
    val userDao: UserDao
) : IUserRepo{
    override suspend fun getAll(): UserModel {
        return userDao.getAll().toUserModel()
    }

    override suspend fun findUserById(id: Int): UserModel {
        return userDao.findUserById(id).toUserModel()
    }

    override suspend fun insert(userModelDto: UserModel) {
        userDao.insert(
            UserModelDto(0,
                userModelDto.name,
                userModelDto.surname,
                userModelDto.image,
                Calendar.getInstance().time.toString(),
                Calendar.getInstance().time.toString()
            )
        )
    }

    override suspend fun update(userModelDto: UserModel) {
        userDao.update(
            UserModelDto(0,
                userModelDto.name,
                userModelDto.surname,
                userModelDto.image,
                Calendar.getInstance().time.toString(),
                Calendar.getInstance().time.toString()
            )
        )
    }

    override suspend fun delete(userModelDto: UserModel) {
        userDao.delete(
            UserModelDto(
                0,
                userModelDto.name,
                userModelDto.surname,
                userModelDto.image,
                Calendar.getInstance().time.toString(),
                Calendar.getInstance().time.toString()
            )
        )
    }
}