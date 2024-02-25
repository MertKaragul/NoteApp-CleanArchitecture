package com.mertkaragul.noteappcleanarchitecture.Domain.Repo

import com.mertkaragul.noteappcleanarchitecture.Domain.Model.UserModel


interface IUserRepo {

    suspend fun getAll() : UserModel

    suspend fun findUserById(id : Int) : UserModel

    suspend fun insert(userModelDto: UserModel)

    suspend fun update(userModelDto: UserModel)

    suspend fun delete(userModelDto: UserModel)
}