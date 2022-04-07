package br.ifsc.edu.crud.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.ifsc.edu.crud.models.User
import br.ifsc.edu.crud.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateEditViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    var user = MutableLiveData<User>()
        private set

    var users = MutableLiveData<List<User>>()
        private set

    fun save(name: String, email: String, data: String, foto: String){
        val user = User(nome = name, email = email, dataNascimento = data, foto = foto)
        repository.create(user = user)
    }

    fun edit(user: User){
        repository.update(user)
    }

    fun getUser(userId: Int){
        user.value = repository.getById(userId)
    }

    fun getAllUsers(){
        users.value = repository.getAll()
    }

    fun delete(user: User){
        repository.delete(user)
    }
}