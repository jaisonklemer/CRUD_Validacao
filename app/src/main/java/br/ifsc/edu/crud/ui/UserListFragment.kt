package br.ifsc.edu.crud.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.ifsc.edu.crud.R
import br.ifsc.edu.crud.adapters.RepoListAdapter
import br.ifsc.edu.crud.databinding.UserListFragmentBinding
import br.ifsc.edu.crud.models.User
import br.ifsc.edu.crud.ui.viewmodels.CreateEditViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment(R.layout.user_list_fragment) {

    private lateinit var binding: UserListFragmentBinding
    private lateinit var viewModel: CreateEditViewModel
    private val adapter = RepoListAdapter{
        editUser(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = UserListFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(CreateEditViewModel::class.java)
        observers()
        setupRecyclerView()
        loadUsers()
    }

    private fun observers(){
        viewModel.users.observe(viewLifecycleOwner){
            adapter.update(it.toMutableList(), true)
        }
    }

    private fun loadUsers(){
        viewModel.getAllUsers()
    }

    private fun setupRecyclerView(){
        binding.listUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.listUsers.adapter = adapter
    }

    private fun editUser(user: User){
        Intent(requireContext(), EditOrCreateActivity::class.java).apply {
            putExtra("user_id", user)
            startActivity(this)
        }
    }
}