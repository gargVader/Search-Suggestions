package com.example.searchsuggestions.presentation.search_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchsuggestions.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()
    lateinit var adapter: SearchSuggestionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.progressBar.isIndeterminate = true
        setupRecyclerView()
        setupEditText()
        setupInputLayout()

        return view
    }

    private fun setupEditText() {
        binding.editText.requestFocus()
//        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)

        binding.editText.addTextChangedListener(afterTextChanged = {
            if (it.toString().isNotEmpty()) binding.searchLayout.isEndIconVisible = true
            adapter.updateQuery(it.toString())
            viewModel.onEvent(SearchScreenEvents.OnQueryUpdated(it.toString()))
        })
        binding.editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                returnQueryToHomeFragment()
                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun setupRecyclerView() {
        adapter = SearchSuggestionsAdapter(
            onSearchClick = {
                // Navigate back to Home with query
                updateEditText(it)
                returnQueryToHomeFragment()
            },
            onFillClick = {
                updateEditText(it)
            }
        )
        val layoutManager = LinearLayoutManager(context)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(
            context, layoutManager.orientation
        )
        binding.recyclerView.addItemDecoration(dividerItemDecoration);

        lifecycleScope.launch {
            viewModel.uiState.collect {
                adapter.data = it.searchSuggestionsList

                if (it.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun setupInputLayout() {
        binding.searchLayout.isEndIconVisible = false
        // Back icon
        binding.searchLayout.setStartIconOnClickListener {
            findNavController().popBackStack()
        }
        // Close icon
        binding.searchLayout.setEndIconOnClickListener {
            updateEditText("")
        }
    }

    private fun updateEditText(string: String) {
        binding.editText.setText(string)
        binding.editText.setSelection(binding.editText.length())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun returnQueryToHomeFragment() {
        findNavController().previousBackStackEntry?.savedStateHandle?.set(
            "query",
            viewModel.uiState.value.query
        )
        findNavController().popBackStack()
    }
}