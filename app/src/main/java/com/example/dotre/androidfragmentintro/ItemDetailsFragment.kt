package com.example.dotre.androidfragmentintro

import android.app.Dialog
import android.app.Fragment
import android.arch.persistence.room.Room
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.*
import android.widget.*
import kotlinx.android.synthetic.main.item_view.*
import kotlinx.android.synthetic.main.list_view.*
import org.jetbrains.anko.*


/**
 * Created by dotre on 01.11.2017.
 */
class ItemDetailsFragment : Fragment(), AdapterView.OnItemClickListener {
    var database: ItemDatabase? = null

    private var itemList: ArrayList<Item> = ArrayList()
    private lateinit var adapter: ItemListAdapter
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View? = inflater?.inflate(R.layout.list_view, container, false)
        database = Room.databaseBuilder(activity.applicationContext, ItemDatabase::class.java, "shop").allowMainThreadQueries().build()
        database?.itemDao()?.clearDB()
        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.add_item, menu)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.add) {
            val spinnerAdapter = ArrayAdapter.createFromResource(context, R.array.item_names, R.layout.spinner_dropdown)
            lateinit var names: Spinner
            alert {
                customView {
                    linearLayout {
                        names = spinner {
                            adapter = spinnerAdapter
                            prompt = "Select itemDao to add"
                        }
                        padding = dip(16)
                    }
                }
                title = "Alert"
                positiveButton("Add") {
                    val item = Item(names.selectedItem.toString(), 2.22)
                    database?.itemDao()?.addItem(item)
                    adapter.lista = database?.itemDao()?.getAll() as ArrayList<Item>
                    adapter.notifyDataSetChanged()
                    toast("Item added")
                }
                negativeButton("Cancel") { toast("Decide!") }
            }.show()
            return true
        }
        return false
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        database.doAsync {
            database?.itemDao()?.addItem(Item(resources.getText(R.string.cola) as String, 2.22))
            database?.itemDao()?.addItem(Item(resources.getText(R.string.fanta) as String, 2.22))
            database?.itemDao()?.addItem(Item(resources.getText(R.string.sprite) as String, 2.22))
            itemList.addAll(database?.itemDao()?.getAll() as ArrayList)
        }
        adapter = ItemListAdapter(context)
        adapter.lista = itemList
        adapter.notifyDataSetChanged()
        listView.adapter = adapter
        listView.onItemClickListener = this
        println(database?.itemDao()?.getAll())
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        listView.setItemChecked(position, true)
        val dialog = Dialog(context)

        dialog.setContentView(R.layout.dialog)

        val text: TextView = dialog.findViewById(R.id.textDialog)
        val image: ImageView = dialog.findViewById(R.id.itemDetailsImage)
        val priceButton: Button = dialog.findViewById(R.id.priceButton)


        priceButton.setOnClickListener({
            toast("Bought")
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