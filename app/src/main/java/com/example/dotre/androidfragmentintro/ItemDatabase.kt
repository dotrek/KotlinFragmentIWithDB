package com.example.dotre.androidfragmentintro

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by dotre on 30.11.2017.
 */
@Database(entities = arrayOf(Item::class), version = 1, exportSchema = false)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

}