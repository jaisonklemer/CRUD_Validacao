package br.ifsc.edu.crud.di

import android.content.Context
import br.ifsc.edu.crud.database.AppDatabase
import br.ifsc.edu.crud.database.dao.UserDao
import br.ifsc.edu.crud.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    fun providePatientDAO(@ApplicationContext context: Context): UserDao {
        return AppDatabase.getInstance(context).userDAO()
    }

    @Provides
    fun provideUserRepository(dao: UserDao): UserRepository {
        return UserRepository(dao)
    }
}