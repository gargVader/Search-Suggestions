package com.example.searchsuggestions.presentation.home_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.searchsuggestions.R
import com.example.searchsuggestions.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.searchLayout.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_search_fragment)
        }
        lifecycleScope.launch {
            viewModel.uiState.collect {
                if (it.query.isBlank()) {
                    binding.searchTextView.text = "Search"
                    binding.searchResultsTextView.text = ""
                } else {
                    binding.searchTextView.text = it.query
                    binding.searchResultsTextView.text = "Showing results for \"${it.query}\""
                }
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("query")
            ?.observe(
                viewLifecycleOwner
            ) { query ->
                viewModel.onEvent(HomeScreenEvents.OnQueryUpdated(query))
                viewModel.performSearch()
            }
    }

}