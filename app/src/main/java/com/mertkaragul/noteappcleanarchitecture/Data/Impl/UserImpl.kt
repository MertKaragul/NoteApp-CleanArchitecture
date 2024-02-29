package com.mertkaragul.noteappcleanarchitecture.Data.Impl

import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.UserModelDto
import com.mertkaragul.noteappcleanarchitecture.Data.Local.Daos.UserDao
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.UserModel
import com.mertkaragul.noteappcleanarchitecture.Domain.Repo.IUserRepo
import java.util.Calendar
import javax.inject.Inject

class UserImpl @Inject constructor(
    private val userDao: UserDao
) : IUserRepo{
    override suspend fun getAll(): List<UserModel> {
        return userDao.getAll()
    }

    override suspend fun findUserById(id: Int): UserModel {
        return userDao.findUserById(id)
    }

    override suspend fun insert(userModel: UserModel) {
        userDao.insert(userModel)
    }

    override suspend fun update(userModelDto: UserModel) {
        userDao.update(
            userModelDto
        )
    }

    override suspend fun delete(userModelDto: UserModel) {
        userDao.delete(
            userModelDto
        )
    }
}