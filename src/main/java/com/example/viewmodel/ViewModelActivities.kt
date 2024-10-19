package com.example.viewmodel

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.viewmodel.databinding.ActivityViewModelsBinding

class ViewModelActivities : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityViewModelsBinding.inflate(layoutInflater)
        binding.root.applyWindowInsets()
        setContentView(binding.root)

        binding.viewModelFactory.setOnClickListener {
            startActivity(Intent(this, ViewModelFactoryActivity::class.java))
        }

        binding.scopedViewModel.setOnClickListener {
            startActivity(Intent(this, ScopedViewModelActivity::class.java))
        }

        binding.savedStateHandle.setOnClickListener {
            startActivity(Intent(this, ViewModelSavedStateHandleActivity::class.java))
        }
    }
}
