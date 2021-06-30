package com.stechoq.todo.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stechoq.todo.core.data.source.local.entity.TodoEntity

@Database(
    entities = [TodoEntity::class], version = 1, exportSchema = false
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}