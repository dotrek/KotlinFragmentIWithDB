package com.example.dotre.androidfragmentintro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.dotre.androidfragmentintro.R.layout.item_view
import kotlinx.android.synthetic.main.item_view.view.*

/**
 * Created by dotre on 01.11.2017.
 */
class ItemListAdapter(context: Context) : BaseAdapter() {
    var lista: ArrayList<Item>? = ArrayList()
    private var inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val rowView: View = inflater.inflate(item_view, parent, false)
        val item: Item = getItem(position)
        rowView.itemName.text = item.itemName
        setImage(rowView, item)
        return rowView
    }

     fun setImage(rowView: View, item: Item) {
        when (item.itemName) {
            "CocaCola" -> {
                rowView.itemImage!!.setImageResource(R.drawable.cola)
            }
            "Fanta" -> {
                rowView.itemImage!!.setImageResource(R.drawable.fanta)
            }
            "Sprite" -> {
                rowView.itemImage!!.setImageResource(R.drawable.sprite)
            }
        }
    }

    override fun getItem(p0: Int): Item {
        return lista?.get(p0)!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong();
    }

    override fun getCount(): Int {
        return lista?.size!!
    }
}