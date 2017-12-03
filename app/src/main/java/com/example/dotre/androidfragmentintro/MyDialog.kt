package com.example.dotre.androidfragmentintro

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.ArrayAdapter
import android.widget.Spinner
import org.jetbrains.anko.*

/**
 * Created by dotre on 03.12.2017.
 */
class MyDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.add_dialog, container)
    }

    companion object {

        lateinit var dialogTitle: String
        fun newInstance(title: String): MyDialog {
            val dialog = MyDialog()
            var args = Bundle()
            dialogTitle = title
            args.putString("dialogTitle", title)
            dialog.arguments = args
            return dialog
        }

    }

}