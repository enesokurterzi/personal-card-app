package com.example.PersonalCardApp.ui.family

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.PersonalCardApp.configs.DbController
import com.example.PersonalCardApp.models.PersonalCard

class FamilyViewModel : ViewModel() {

    var familyCards: MutableLiveData<List<PersonalCard>> = MutableLiveData()

    fun getByGroup(context: Context){
        familyCards.value = DbController(context).db.personelCardDao().getByGroup("Family")
    }

}