package com.mertkaragul.noteappcleanarchitecture.Data.Local.Daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.UserModelDto
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.UserModel


@Dao
interface UserDao {
    @Query("SELECT * FROM UserModel")
    suspend fun getAll() : List<UserModel>
    @Query("SELECT * FROM UserModel WHERE id LIKE :id")
    suspend fun findUserById(id : Int) : UserModel
    @Insert
    suspend fun insert(userModel: UserModel)
    @Update
    suspend fun update(userModel: UserModel)
    @Delete
    suspend fun delete(userModel: UserModel)
}