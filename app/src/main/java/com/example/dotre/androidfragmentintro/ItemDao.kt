package com.example.dotre.androidfragmentintro

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Created by dotre on 30.11.2017.
 */
@Dao
interface ItemDao {
    @Query("SELECT * from items")
    fun getAll(): List<Item>

    @Query("SELECT item_name FROM items i WHERE i.id=:id")
    fun getItemName(id: Int): String

    @Insert
    fun addItem(item: Item)

    @Delete
    fun deleteItem(item: Item)
}