package com.task.roomapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task.roomapp.model.Rooms


@Database(entities = [Rooms::class], version = 1, exportSchema = false)
abstract class RoomsDatabase : RoomDatabase() {

    abstract fun roomsDao(): RoomsEntity

}