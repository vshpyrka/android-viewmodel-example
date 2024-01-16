package com.example.viewmodel

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.viewmodel.databinding.ActivityViewModelScopedBinding
import com.example.viewmodel.databinding.FragmentViewModelParentContainerBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ScopedViewModelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityViewModelScopedBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

class SharedViewModel : ViewModel() {
    // Expose screen UI state
    data class UiState(val string: String? = null)

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun setString(str: String) {
        _uiState.update { currentState ->
            currentState.copy(string = str)
        }
    }
}

class ParentFragment : Fragment(R.layout.fragment_view_model_parent_container) {

    private val sharedViewModel by viewModels<SharedViewModel>(
        ownerProducer = { this },
    )

    private var _binding: FragmentViewModelParentContainerBinding? = null
    private val binding: FragmentViewModelParentContainerBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentViewModelParentContainerBinding.bind(view)

        viewLifecycleOwner.lifecycleScope.launch {
            sharedViewModel
                .uiState
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect {
                    binding.childContainerText.text = it.string
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

class NestedFragment : Fragment() {

    // ViewModel API available in fragment.fragment-ktx
    // The ViewModel is scoped to the parent of the current Fragment
    private val viewModel: SharedViewModel by viewModels(
        ownerProducer = {
            requireParentFragment()
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setString("This is text sent from nested fragment and observed in parent fragment")
    }
}
