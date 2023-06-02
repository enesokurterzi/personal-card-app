package com.example.PersonalCardApp.ui.updatepersonalcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.example.PersonalCardApp.configs.DbController
import com.example.PersonalCardApp.configs.Util
import com.example.PersonalCardApp.databinding.ActivityUpdatePersonalCardBinding
import com.google.android.material.snackbar.Snackbar

class UpdatePersonalCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdatePersonalCardBinding
    private val chosen = Util.chosen!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatePersonalCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContent()

        binding.btnUpdateChooseGroup.setOnClickListener {
            showPopup(binding.btnUpdateChooseGroup)
        }

        binding.btnUpdateItem.setOnClickListener {
            updatePersonalCard()
        }

    }

    private fun updatePersonalCard() {
        val name = binding.txtUpdateName.text.toString()
        val surName = binding.txtUpdateSurname.text.toString()
        val phone = binding.txtUpdatePhone.text.toString()
        val address = binding.txtUpdateAddress.text.toString()
        val group = binding.btnUpdateChooseGroup.text.toString()


        if (name.isNotEmpty()
            && surName.isNotEmpty()
            && phone.isNotEmpty()
            && address.isNotEmpty()) {

            chosen.name = name
            chosen.surName = surName
            chosen.phone = phone.toInt()
            chosen.address = address
            chosen.group = group

            DbController(applicationContext).db.personelCardDao().update(chosen)
            Util.chosen = chosen

            finishAndReturn()

        } else {
            Toast.makeText(this, "Please check your inputs!", Toast.LENGTH_LONG).show()
        }


    }

    private fun finishAndReturn() {
        val view = findViewById<View>(android.R.id.content)
        Snackbar.make(view, "Successfully Updated", Snackbar.LENGTH_LONG).show()
        view.postDelayed({
            finish()
        }, 1000)
    }

    private fun showPopup(view: View) {
        val popupMenu = PopupMenu(this, view)
        val groupList = listOf("Family", "Friends", "School", "Work")

        groupList.forEach { item ->
            val menuItem = popupMenu.menu.add(item)
            menuItem.setOnMenuItemClickListener {
                binding.btnUpdateChooseGroup.text = item
                true
            }
        }
        popupMenu.show()
    }

    private fun setContent() {
        title = "Update Person"
        binding.apply {
            txtUpdateName.editableText.append(chosen.name)
            txtUpdateSurname.editableText.append(chosen.surName)
            txtUpdatePhone.editableText.append(chosen.phone.toString())
            txtUpdateAddress.editableText.append(chosen.address)
            btnUpdateChooseGroup.text = chosen.group
        }

    }
}