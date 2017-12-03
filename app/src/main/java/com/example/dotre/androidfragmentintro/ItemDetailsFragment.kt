package com.example.dotre.androidfragmentintro

import android.app.AlertDialog
import android.app.Dialog
import android.app.Fragment
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.*
import android.widget.*
import kotlinx.android.synthetic.main.list_view.*
import org.jetbrains.anko.doAsync


/**
 * Created by dotre on 01.11.2017.
 */
class ItemDetailsFragment : Fragment(), AdapterView.OnItemClickListener {

    var database: ItemDao? = null

    private var itemList: ArrayList<Item> = ArrayList()
    private lateinit var adapter: ItemListAdapter
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View? = inflater?.inflate(R.layout.list_view, container, false)
        database = ItemDatabase.getInstance(context)
        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.add_item, menu)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.add) {
            (activity as MainActivity).showAddDialog()
            return true
        }
        return false
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        database.doAsync {
            database?.addItem(Item(resources.getText(R.string.cola) as String, 2.22))
            database?.addItem(Item(resources.getText(R.string.fanta) as String, 2.22))
            database?.addItem(Item(resources.getText(R.string.sprite) as String, 2.22))
            itemList = database?.getAll() as ArrayList<Item>
        }
        adapter = ItemListAdapter(context)
        adapter.lista = itemList
        listView.adapter = adapter
        listView.onItemClickListener = this

    }

//    fun CheckIsDataAlreadyInDBorNot(TableName: String,
//                                    dbfield: String, fieldValue: String): Boolean {
//        val sqldb = .sqLiteDatabase
//        val Query = "Select * from $TableName where $dbfield = $fieldValue"
//        val cursor = sqldb.rawQuery(Query, null)
//        if (cursor.getCount() <= 0) {
//            cursor.close()
//            return false
//        }
//        cursor.close()
//        return true
//    }


    override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        listView.setItemChecked(position, true)
        val dialog = Dialog(context)

        dialog.setContentView(R.layout.dialog)

        val text: TextView = dialog.findViewById(R.id.textDialog)
        val image: ImageView = dialog.findViewById(R.id.itemDetailsImage)
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