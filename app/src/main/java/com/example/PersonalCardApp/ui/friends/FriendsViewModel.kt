package com.example.PersonalCardApp.ui.friends

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.PersonalCardApp.configs.DbController
import com.example.PersonalCardApp.models.PersonalCard

class FriendsViewModel : ViewModel() {

    var friendsCards: MutableLiveData<List<PersonalCard>> = MutableLiveData()

    fun getByGroup(context: Context){
        friendsCards.value = DbController(context).db.personelCardDao().getByGroup("Friends")
    }
}