package com.task.roomapp

import androidx.annotation.WorkerThread
import com.task.roomapp.model.Rooms
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RoomsRepository @Inject constructor(
    private val roomsDao: RoomsEntity
) {

    val allRooms: Flow<MutableList<Rooms>> = roomsDao.getAllRooms()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(rooms: Rooms) {
        roomsDao.insertRooms(rooms)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(rooms: Rooms) {
        roomsDao.updateRooms(rooms)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun isDataExist(name: String?): Rooms? {
        return roomsDao.isDataExist(name)
    }
}