package com.task.roomapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.task.roomapp.model.Rooms
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomsEntity {

    @Query("SELECT * FROM rooms ORDER BY CASE WHEN isLive = 1 THEN 0 ELSE 1 END, utcTimestamp DESC")
    fun getAllRooms(): Flow<MutableList<Rooms>>

    @Insert
    fun insertRooms(rooms: Rooms)

    @Query("SELECT * FROM rooms WHERE name = :name")
    fun isDataExist(name: String?): Rooms?

    @Update
    fun updateRooms(rooms: Rooms)
}