package com.example.dotre.androidfragmentintro

import android.app.Dialog
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.list_view.*


/**
 * Created by dotre on 01.11.2017.
 */
class ItemDetailsFragment : Fragment(), AdapterView.OnItemClickListener {

    var database: ItemDao? = null
    private lateinit var itemList: ArrayList<Item>
    private lateinit var adapter: ItemListAdapter
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var rootView: View = inflater!!.inflate(R.layout.list_view, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = ItemDatabase.getInstance(context)
        itemList = database?.getAll() as ArrayList<Item>
        itemList.run {
            add(Item(resources.getText(R.string.cola) as String, 2.22))
            add(Item(resources.getText(R.string.fanta) as String, 2.22))
            add(Item(resources.getText(R.string.sprite) as String, 2.22))
            add(Item(resources.getText(R.string.cola) as String, 2.22))
            add(Item(resources.getText(R.string.fanta) as String, 2.22))
            add(Item(resources.getText(R.string.sprite) as String, 2.22))
            add(Item(resources.getText(R.string.cola) as String, 2.22))
            add(Item(resources.getText(R.string.fanta) as String, 2.22))
            add(Item(resources.getText(R.string.sprite) as String, 2.22))
            add(Item(resources.getText(R.string.cola) as String, 2.22))
            add(Item(resources.getText(R.string.fanta) as String, 2.22))
            add(Item(resources.getText(R.string.sprite) as String, 2.22))
        }

        adapter = ItemListAdapter(context)
        adapter.lista = itemList
        listView.adapter = adapter
        listView.onItemClickListener = this
    }


    override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        listView.setItemChecked(position, true)
        val dialog = Dialog(context)

        dialog.setContentView(R.layout.dialog)

        val text: TextView = dialog.findViewById(R.id.textDialog)
        var image: ImageView = dialog.findViewById(R.id.itemDetailsImage)
        val priceButton: Button = dialog.findViewById(R.id.priceButton)

        priceButton.setOnClickListener({
            Toast.makeText(context, "Bought!", Toast.LENGTH_SHORT).show()
        })

        setImage(image, itemList[position])
        text.text = itemList[position].itemName
        priceButton.text = itemList[position].price.toString()
        dialog.show()
    }

    private fun setImage(image: ImageView, item: Item) {
        when (item.itemName) {
            resources.getText(R.string.cola) -> {
                image.setImageResource(R.drawable.cola)
            }
            resources.getText(R.string.fanta) -> {
                image.setImageResource(R.drawable.fanta)
            }
            resources.getText(R.string.sprite) -> {
                image.setImageResource(R.drawable.sprite)
            }
        }
    }
}