package br.ifsc.edu.crud.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id: Int = 0,
    var nome: String,
    var email: String,
    var dataNascimento: String,
    var foto: String = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRnJDK2azmmfkbFYv8t-31oopd488mLSBMCacXmAmeIHX41HxWnzbakuuCEilPNooWAWAM&usqp=CAU"
) : Serializable