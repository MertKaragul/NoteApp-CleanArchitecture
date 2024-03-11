package com.mertkaragul.noteappcleanarchitecture.Data.Local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mertkaragul.noteappcleanarchitecture.Data.Local.Daos.NoteDao
import com.mertkaragul.noteappcleanarchitecture.Domain.Model.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class Database : RoomDatabase(){
    abstract fun noteDao() : NoteDao
}