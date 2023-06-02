package com.example.PersonalCardApp.ui.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.PersonalCardApp.configs.DbController
import com.example.PersonalCardApp.models.PersonalCard

class HomeViewModel() : ViewModel() {

    var homeCards: MutableLiveData<List<PersonalCard>> = MutableLiveData()

    fun getLastTen(context: Context) {
        homeCards.value = DbController(context).db.personelCardDao().getLastTen()
    }

    fun search(context: Context, searchString: String) {
        homeCards.value = DbController(context).db.personelCardDao().search(searchString)
    }
}