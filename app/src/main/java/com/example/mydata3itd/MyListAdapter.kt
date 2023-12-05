package com.example.mydata3itd

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyListAdapter(private val context: Activity, private val id: Array<String>, private val name: Array<String>, private val email: Array<String>)
    :ArrayAdapter<String>(context, R.layout.custom_list, name){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list,null, true)

        val idText = rowView.findViewById<TextView>(R.id.textViewId)
        val nameText = rowView.findViewById<TextView>(R.id.textViewName)
        val emailText = rowView.findViewById<TextView>(R.id.textViewEmail)

        idText.text = "Id: ${id[position]}"
        nameText.text = "Name: ${name[position]}"
        emailText.text = "Email: ${email[position]}"

        return rowView
    }
}