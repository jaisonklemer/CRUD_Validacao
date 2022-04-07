package br.ifsc.edu.crud.repository

import br.ifsc.edu.crud.database.dao.UserDao
import br.ifsc.edu.crud.models.User
import javax.inject.Inject

class UserRepository @Inject constructor(private val dao: UserDao) {

    fun create(user: User) {
        dao.insert(user)
    }

    fun getById(id: Int): User {
        return dao.getById(id)
    }

    fun getAll() : List<User>{
        return dao.getAll()
    }

    fun update(user: User){
       dao.update(user)
    }

    fun delete(user: User){
        dao.delete(user)
    }
}