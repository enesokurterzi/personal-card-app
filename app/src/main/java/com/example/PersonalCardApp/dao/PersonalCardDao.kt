package com.example.PersonalCardApp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.PersonalCardApp.models.PersonalCard

@Dao
interface PersonalCardDao {

    @Insert
    fun insert(personalCard: PersonalCard): Long

    @Query("SELECT * FROM personalCard ORDER BY id DESC LIMIT 10")
    fun getLastTen(): List<PersonalCard>

    @Delete
    fun delete(personalCard: PersonalCard)

    @Update
    fun update(personalCard: PersonalCard)

    @Query("SELECT * FROM personalCard WHERE `group` = :group")
    fun getByGroup(group: String): List<PersonalCard>

    @Query("SELECT * FROM personalCard WHERE name LIKE '%' || :searchString || '%' " +
            "OR surName LIKE '%' || :searchString || '%'")
    fun search(searchString: String): List<PersonalCard>



}