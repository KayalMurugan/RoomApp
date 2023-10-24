package com.task.roomapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rooms")
class Rooms(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val isLive: Boolean,
    val utcTimestamp: Long
)