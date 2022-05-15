package com.example.parking.ui.views

import android.app.Activity
import android.app.AlertDialog
import com.example.parking.R

class Backdrop(private val activity: Activity?) {

    private var alertDialog: AlertDialog? = AlertDialog.Builder(activity)
        .setView(activity?.layoutInflater?.inflate(R.layout.backdrop, null))
        .setCancelable(false)
        .create();

    fun show(){
        alertDialog?.show()
    }

    fun dismiss(){
        alertDialog?.dismiss()
    }

}