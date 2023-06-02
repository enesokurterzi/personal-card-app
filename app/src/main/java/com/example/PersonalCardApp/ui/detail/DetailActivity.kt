package com.example.PersonalCardApp.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.PersonalCardApp.configs.DbController
import com.example.PersonalCardApp.configs.Util
import com.example.PersonalCardApp.databinding.ActivityDetailBinding
import com.example.PersonalCardApp.ui.updatepersonalcard.UpdatePersonalCardActivity
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val chosen = Util.chosen!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContent()

        binding.btnDelete.setOnClickListener {
            DbController(applicationContext).db.personelCardDao().delete(chosen)
            val view = findViewById<View>(android.R.id.content)
            Snackbar.make(view, "Successfully Deleted", Snackbar.LENGTH_LONG).show()
            view.postDelayed({
                finish()
            }, 1000)
        }

        binding.btnUpdate.setOnClickListener {
            val intent = Intent(this, UpdatePersonalCardActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        setContent()
    }
    private fun setContent() {
        title = "Detail"

        binding.apply {
            txtDetailName.text = "Name: ${chosen.name}"
            txtDetailSurname.text = "Surname: ${chosen.surName}"
            txtDetailGroup.text = "Group: ${chosen.group}"
            txtDetailPhone.text = "Phone: ${chosen.phone}"
            txtDetailAddress.text = "Address: ${chosen.address}"
        }
    }
}