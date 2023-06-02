package com.example.PersonalCardApp.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.PersonalCardApp.R
import com.example.PersonalCardApp.models.PersonalCard

class PersonalCardAdapter(private val context: Activity, private val list: List<PersonalCard>, private val fragmentName: String) :
    ArrayAdapter<PersonalCard>(context, R.layout.personel_card_list_item, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = context.layoutInflater.inflate(R.layout.personel_card_list_item, null, true)

        val txtListItemName = rootView.findViewById<TextView>(R.id.txtListItemName)
        val txtListItemSurname = rootView.findViewById<TextView>(R.id.txtListItemSurname)
        val txtListItemGroup = rootView.findViewById<TextView>(R.id.txtListItemGroup)
        val txtListItemPhone = rootView.findViewById<TextView>(R.id.txtListItemPhone)
        val txtListItemAddress = rootView.findViewById<TextView>(R.id.txtListItemAddress)

        val personalCard = list[position]

        txtListItemName.text = personalCard.name
        txtListItemSurname.text = personalCard.surName
        txtListItemGroup.text = personalCard.group
        txtListItemPhone.text = personalCard.phone.toString()
        txtListItemAddress.text = personalCard.address

        if (fragmentName != "home") {
            txtListItemGroup.visibility = View.INVISIBLE
        }

        return rootView
    }
}