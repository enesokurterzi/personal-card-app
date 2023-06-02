package com.example.PersonalCardApp.configs

import android.app.Activity
import android.content.Context
import androidx.room.Room

class DbController(context: Context): Activity() {
        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "appDataBase"
        )
            .allowMainThreadQueries()
            .build()
}