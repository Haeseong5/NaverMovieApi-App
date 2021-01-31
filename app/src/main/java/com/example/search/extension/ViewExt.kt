package com.example.search.extension

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun showSnack(view: View, msg : String){
    Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction("RETRY",null).show()
}

fun showToast(context : Context, msg:String){
    Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
}

fun showToast(context : Context, resourceId:Int){
    Toast.makeText(context,resourceId,Toast.LENGTH_SHORT).show()
}
