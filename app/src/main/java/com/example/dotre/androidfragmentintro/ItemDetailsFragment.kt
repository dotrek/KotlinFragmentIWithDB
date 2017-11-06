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

    private var itemList: ArrayList<Item> = ArrayList()
    private lateinit var adapter: ItemListAdapter
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var rootView: View = inflater!!.inflate(R.layout.list_view, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemList.add(Item("CocaCola", null, 2.22))
        itemList.add(Item("Fanta", null, 2.22))
        itemList.add(Item("Sprite", null, 2.22))
        itemList.add(Item("CocaCola", null, 2.22))
        itemList.add(Item("Fanta", null, 2.22))
        itemList.add(Item("Sprite", null, 2.22))
        itemList.add(Item("CocaCola", null, 2.22))
        itemList.add(Item("Fanta", null, 2.22))
        itemList.add(Item("Sprite", null, 2.22))
        itemList.add(Item("CocaCola", null, 2.22))
        itemList.add(Item("Fanta", null, 2.22))
        itemList.add(Item("Sprite", null, 2.22))

        adapter = ItemListAdapter(context)
        (adapter as ItemListAdapter).lista = itemList
        listView.adapter = adapter
        listView.onItemClickListener = this
    }


    override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        listView.setItemChecked(position, true)
        val dialog = Dialog(context)
        // Include dialog.xml file
        dialog.setContentView(R.layout.dialog) // layout of your dialog
        // Set dialog title
        dialog.setTitle("Detail")

        // set values for custom dialog components - text, image and button
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
            "CocaCola" -> {
                image.setImageResource(R.drawable.cola)
            }
            "Fanta" -> {
                image.setImageResource(R.drawable.fanta)
            }
            "Sprite" -> {
                image.setImageResource(R.drawable.sprite)
            }
        }
    }
}