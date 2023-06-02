package com.example.PersonalCardApp.configs

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.PersonalCardApp.dao.PersonalCardDao
import com.example.PersonalCardApp.models.PersonalCard

@Database(entities = [PersonalCard::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun personelCardDao(): PersonalCardDao

}