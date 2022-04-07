package br.ifsc.edu.crud.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import br.ifsc.edu.crud.R
import br.ifsc.edu.crud.databinding.ActivityMainBinding
import br.ifsc.edu.crud.models.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtons()
    }

    override fun onResume() {
        super.onResume()
        setupListFragment()
    }


    private fun setupButtons() {
        binding.btnCreate.setOnClickListener {
            Intent(this, EditOrCreateActivity::class.java).apply {
                startActivity(this)
            }
        }

        binding.btnList.setOnClickListener {
            setupListFragment()
        }
    }

    private fun setupListFragment(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_root, UserListFragment())
            .commit()

    }
}
