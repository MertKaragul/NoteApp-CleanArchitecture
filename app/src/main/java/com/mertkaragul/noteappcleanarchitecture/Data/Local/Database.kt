package com.mertkaragul.noteappcleanarchitecture.Data.Local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.NoteModelDto
import com.mertkaragul.noteappcleanarchitecture.Data.Local.DTO.UserModelDto
import com.mertkaragul.noteappcleanarchitecture.Data.Local.Daos.NoteDao
import com.mertkaragul.noteappcleanarchitecture.Data.Local.Daos.UserDao
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.NoteModel
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.UserModel

@Database(entities = [NoteModel::class, UserModel::class], version = 1)
abstract class Database : RoomDatabase(){
    abstract fun noteDao() : NoteDao
    abstract fun userDao() : UserDao
}