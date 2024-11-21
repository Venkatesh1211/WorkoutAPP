package com.example.workoutapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface PersonDao {
    @Insert
    suspend fun insert(person: Person)

    @Update
    suspend fun update(person: Person)

    @Delete
    suspend fun delete(person: Person)

    @Query("SELECT * FROM person WHERE id = :id")
    suspend fun getPersonById(id: Int): Person?

    @Query("SELECT * FROM person")
    suspend fun getAllPersons(): List<Person>
}
