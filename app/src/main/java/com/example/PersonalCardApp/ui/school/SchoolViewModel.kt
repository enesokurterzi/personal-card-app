package com.example.PersonalCardApp.ui.school

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.PersonalCardApp.configs.DbController
import com.example.PersonalCardApp.models.PersonalCard

class SchoolViewModel : ViewModel() {

    var schoolCards: MutableLiveData<List<PersonalCard>> = MutableLiveData()

    fun getByGroup(context: Context){
        schoolCards.value = DbController(context).db.personelCardDao().getByGroup("School")
    }
}