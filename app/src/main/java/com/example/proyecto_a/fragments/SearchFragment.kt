package com.example.proyecto_a.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.node.ViewAdapter
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto_a.R
import com.example.proyecto_a.activities.MainActivity
import com.example.proyecto_a.adapters.MealsAdapter
import com.example.proyecto_a.databinding.FragmentSearchBinding
import com.example.proyecto_a.videoModel.HomeViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var searchRecyclerViewAdapter: MealsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()

        binding.imgSearchedArrow.setOnClickListener{ searchMeals() }

        observeSearchedMealsliveData()

        var searchJob:Job? = null
        binding.edSearchBox.addTextChangedListener{ searchQuery ->
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                viewModel.searchMeals(searchQuery.toString())
            }
        }

    }

    private fun observeSearchedMealsliveData() {
        viewModel.observeSearchedMealsLivedata().observe(viewLifecycleOwner, Observer {mealsList ->

            searchRecyclerViewAdapter.differ.submitList(mealsList)
        })
    }

    private fun searchMeals() {
        val searchQuery = binding.edSearchBox.text.toString()
        if (searchQuery.isNotEmpty()){
            viewModel.searchMeals(searchQuery)
        }
    }

    private fun prepareRecyclerView() {
        searchRecyclerViewAdapter = MealsAdapter()
        binding.rvSearchedMeals.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = searchRecyclerViewAdapter
        }
    }
}