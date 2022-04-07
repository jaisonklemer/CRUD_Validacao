package br.ifsc.edu.crud.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.ifsc.edu.crud.database.dao.UserDao
import br.ifsc.edu.crud.models.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDao

    companion object {

        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "crud_db"
            )
                .allowMainThreadQueries()
                .build()
        }
    }


}