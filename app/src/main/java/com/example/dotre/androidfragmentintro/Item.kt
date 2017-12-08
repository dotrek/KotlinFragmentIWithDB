package com.example.dotre.androidfragmentintro

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by dotre on 01.11.2017.
 */
@Entity(tableName = "items")
data class Item(@ColumnInfo(name = "item_name") var itemName: String?,
                @ColumnInfo(name = "item_price") var price: Double?) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    override fun toString(): String {
        return itemName.toString()
    }
}