package com.example.dotre.androidfragmentintro

import android.app.FragmentManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    fun showAddDialog() {
//        val fm = fragmentManager as FragmentManager
//        val addItemDialogFragment = MyDialog.newInstance("Add new itemDao")
//        addItemDialogFragment.show(fm, "add")
//    }
}
