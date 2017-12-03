package com.example.dotre.androidfragmentintro

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by dotre on 30.11.2017.
 */
@Database(entities = arrayOf(Item::class), version = 1, exportSchema = false)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        private val databaseName = "shop"

        var dbInstance: ItemDao? = null
        fun getInstance(context: Context): ItemDao? {
            if (dbInstance == null)
                synchronized(ItemDatabase::class) {
                    dbInstance = Room.databaseBuilder(context, ItemDatabase::class.java, databaseName).build().itemDao()
                }
            return dbInstance;
        }
    }
}