package com.example.PersonalCardApp.ui.addpersonalcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.example.PersonalCardApp.configs.DbController
import com.example.PersonalCardApp.databinding.ActivityAddPersonalCardBinding
import com.example.PersonalCardApp.models.PersonalCard
import com.google.android.material.snackbar.Snackbar

class AddPersonalCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPersonalCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPersonalCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "New Person"

        binding.btnChooseGroup.setOnClickListener {
            showPopup(binding.btnChooseGroup)
        }

        binding.btnAddItem.setOnClickListener {
            addPersonalCard()
        }
    }

    private fun showPopup(view: View) {
        val popupMenu = PopupMenu(this, view)
        val groupList = listOf("Family", "Friends", "School", "Work")

        groupList.forEach { item ->
            val menuItem = popupMenu.menu.add(item)
            menuItem.setOnMenuItemClickListener {
                binding.btnChooseGroup.text = item
                true
            }
        }
        popupMenu.show()
    }

    private fun addPersonalCard() {
        val name = binding.txtName.text.toString()
        val surName = binding.txtSurname.text.toString()
        val phone = binding.txtPhone.text.toString()
        val address = binding.txtAddress.text.toString()
        val group = binding.btnChooseGroup.text.toString()

        if (name.isNotEmpty()
            && surName.isNotEmpty()
            && phone.isNotEmpty()
            && address.isNotEmpty()
            && group != "Choose") {

            val personalCard = PersonalCard(null, name, surName, phone.toInt(), address, group)
            DbController(applicationContext).db.personelCardDao().insert(personalCard)

            finishAndReturn()

        } else {
            Toast.makeText(this, "Please check your inputs!", Toast.LENGTH_LONG).show()
        }

    }

    private fun finishAndReturn() {
        val view = findViewById<View>(android.R.id.content)
        Snackbar.make(view, "Successfully Added", Snackbar.LENGTH_LONG).show()
        view.postDelayed({
            finish()
        }, 1000)
    }

}