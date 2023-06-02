package com.example.PersonalCardApp.ui.work

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.PersonalCardApp.configs.DbController
import com.example.PersonalCardApp.models.PersonalCard

class WorkViewModel : ViewModel() {

    var workCards: MutableLiveData<List<PersonalCard>> = MutableLiveData()

    fun getByGroup(context: Context){
        workCards.value = DbController(context).db.personelCardDao().getByGroup("Work")
    }

}