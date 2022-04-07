package br.ifsc.edu.crud.ui

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import br.ifsc.edu.crud.R
import br.ifsc.edu.crud.databinding.ActivityEditOrCreateBinding
import br.ifsc.edu.crud.models.User
import br.ifsc.edu.crud.ui.viewmodels.CreateEditViewModel
import br.ifsc.edu.crud.utils.ImageUtil
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class EditOrCreateActivity : AppCompatActivity() {

    private lateinit var etNome: EditText
    private lateinit var etEmail: EditText
    private lateinit var etData: EditText
    private lateinit var binding: ActivityEditOrCreateBinding
    private lateinit var viewModel: CreateEditViewModel
    private var userModel: User? = null
    private var isEditMode: Boolean = false
    private lateinit var startGallery: ActivityResultLauncher<Intent>
    private var selectedImage : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditOrCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(CreateEditViewModel::class.java)
        getExtra()
        initViews()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        registerGalleryActivity()
        requestPermissions()
    }


    private fun initViews() {
        etNome = findViewById(R.id.et_nome)
        etEmail = findViewById(R.id.et_email)
        etData = findViewById(R.id.et_data)


        binding.etData.listen()

        setupButtons()
        setupUserModel()
    }

    private fun setupUserModel() {
        userModel?.let {
            isEditMode = true
            binding.etNome.setText(it.nome)
            binding.etData.setText(it.dataNascimento)
            binding.etEmail.setText(it.email)

            binding.userImage.apply {
                this.load(File(it.foto))
            }
        }
    }

    private fun setupButtons() {
        binding.btSalvar.setOnClickListener {
            if (isEditMode) {
                editUser()
            } else {
                saveUser()
            }

        }

        binding.btApagar.setOnClickListener {
            viewModel.delete(userModel!!)
            finish()
        }

        binding.changeImage.setOnClickListener {
            openGallery()
        }
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
            ),
            1
        )
    }

    private fun editUser(){
        if (checkFields()) {
            val nome = etNome.text.toString()
            val email = etEmail.text.toString()
            val data = etData.text.toString()

            userModel!!.nome = nome
            userModel!!.email = email
            userModel!!.dataNascimento = data
            userModel!!.foto = selectedImage!!

            viewModel.edit(userModel!!)
            finish()
        }
    }

    private fun saveUser() {
        if (checkFields()) {
            val nome = etNome.text.toString()
            val email = etEmail.text.toString()
            val data = etData.text.toString()

            viewModel.save(nome, email, data, selectedImage!!)
            finish()
        }
    }

    private fun checkFields(): Boolean {
        return !etNome.text.isNullOrEmpty()
                && !etEmail.text.isNullOrEmpty()
                && !etData.text.isNullOrEmpty()
    }

    private fun getExtra() {
        userModel = intent.getSerializableExtra("user_id") as User?
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startGallery.launch(gallery)
    }

    private fun updateImage(url: String) {
        binding.userImage.apply {
            this.load(url)
        }
    }

    private fun registerGalleryActivity() {
        startGallery =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->

                if (result.resultCode == RESULT_OK) {

                    val imageUri = result.data?.data

                    selectedImage = ImageUtil.getRealPathFromURI(contentResolver, imageUri!!)
                    updateImage(selectedImage!!)
                }
            }
    }
}