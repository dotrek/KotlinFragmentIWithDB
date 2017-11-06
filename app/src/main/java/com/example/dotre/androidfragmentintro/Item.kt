package com.example.dotre.androidfragmentintro

import android.widget.ImageView

/**
 * Created by dotre on 01.11.2017.
 */
class Item(itemName: String, itemImage: ImageView?, price: Double) {
    var itemName: String = itemName
        get() = field ?: "Just Item"
        set(value) {
            this.itemName = value
        }
    var price: Double = price
        set(value) {
            this.price = value
        }
    override fun toString(): String {
        return itemName + " which costs " + price.toString()
    }
}