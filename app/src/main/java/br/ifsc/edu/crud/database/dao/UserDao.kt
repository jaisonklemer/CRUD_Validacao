package br.ifsc.edu.crud.database.dao

import androidx.room.*
import br.ifsc.edu.crud.models.User

@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM USER")
    fun getAll(): List<User>

    @Query("SELECT * FROM USER WHERE user_id = :id")
    fun getById(id: Int) : User

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}