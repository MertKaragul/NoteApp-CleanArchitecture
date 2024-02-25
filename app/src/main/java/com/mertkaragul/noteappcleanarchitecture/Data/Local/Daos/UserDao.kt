package com.mertkaragul.noteappcleanarchitecture.Data.Local.Daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.UserModelDto


@Dao
interface UserDao {
    @Query("SELECT * FROM NoteModelDto")
    suspend fun getAll() : UserModelDto
    @Query("SELECT * FROM NoteModelDto WHERE id LIKE :id")
    suspend fun findUserById(id : Int) : UserModelDto
    @Insert
    suspend fun insert(userModelDto: UserModelDto)
    @Update
    suspend fun update(userModelDto: UserModelDto)
    @Delete
    suspend fun delete(userModelDto: UserModelDto)
}