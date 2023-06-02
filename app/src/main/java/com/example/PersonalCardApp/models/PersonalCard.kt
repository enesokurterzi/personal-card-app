package com.example.PersonalCardApp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personalCard")
data class PersonalCard(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    var name: String?,
    var surName: String?,
    var phone: Int?,
    var address: String?,
    var group: String?

)
