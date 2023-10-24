package com.task.roomapp.di

import android.content.Context
import androidx.room.Room
import com.task.roomapp.RoomsDatabase
import com.task.roomapp.RoomsEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomsModule {

    @Provides
    fun provideRoomsDatabase(@ApplicationContext context: Context): RoomsDatabase {
        return Room.databaseBuilder(context, RoomsDatabase::class.java, "rooms.db")
            .build()
    }

    @Provides
    fun provideRoomsDao(roomsDatabase: RoomsDatabase): RoomsEntity {
        return roomsDatabase.roomsDao()
    }
}