package com.example.proyecto_a.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.proyecto_a.R
import com.example.proyecto_a.activities.MainActivity
import com.example.proyecto_a.adapters.CategoriesAdapter
import com.example.proyecto_a.databinding.FragmentCategoriesBinding
import com.example.proyecto_a.videoModel.HomeViewModel

class CategoriesFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding:FragmentCategoriesBinding
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel



        requireArguments().let {
            var ARG_PARAM1 = null
            param1 = it.getString(ARG_PARAM1)
            var ARG_PARAM2 = null
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()

        observeCategories()
    }

    private fun observeCategories() {
        viewModel.observeCategoriesLiveData().observe(viewLifecycleOwner, Observer { categories->
            categoriesAdapter.setCategoryList(categories)

        })
    }

    private fun prepareRecyclerView() {
        categoriesAdapter = CategoriesAdapter()
        binding.rvCategories.apply{
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL,false)
            adapter = categoriesAdapter
        }

    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            CategoriesFragment().apply {
                arguments = Bundle().apply {
                    var ARG_PARAM1 = null
                    putString(ARG_PARAM1, param1)
                    var ARG_PARAM2 = null
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
