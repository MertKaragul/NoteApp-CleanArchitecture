package com.mertkaragul.noteappcleanarchitecture.Data.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.mertkaragul.noteappcleanarchitecture.Common.Constants
import com.mertkaragul.noteappcleanarchitecture.Data.Impl.NoteImpl
import com.mertkaragul.noteappcleanarchitecture.Data.Local.Database
import com.mertkaragul.noteappcleanarchitecture.Domain.Repo.INoteRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Di {

    @Singleton
    @Provides
    fun ProvideDatabase(
        @ApplicationContext context : Context
    ) : Database {
        return Room
            .databaseBuilder(context, Database::class.java, Constants.DATABASE_NAME)
            .build()
    }

    @Provides
    fun ProvideGson() : Gson{
        return Gson()
    }

    @Singleton
    @Provides
    fun ProvideNoteImpl(database: Database) : INoteRepo {
        return NoteImpl(database.noteDao())
    }
}