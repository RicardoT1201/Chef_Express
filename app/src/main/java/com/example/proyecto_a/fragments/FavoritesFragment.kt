package com.example.proyecto_a.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.example.proyecto_a.activities.MainActivity
import com.example.proyecto_a.adapters.MealsAdapter
import com.example.proyecto_a.databinding.FragmentFavoritesBinding
import com.example.proyecto_a.videoModel.HomeViewModel

class FavoritesFragment : Fragment() {
    private lateinit var binding:FragmentFavoritesBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var  favoriteAdapter:MealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        }
    }

    fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var binding = FragmentFavoritesBinding.inflate(inflater)
        return binding.root
    }

    fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val onViewCreated = super.onViewCreated(view, savedInstanceState)observeFavorites()


        private fun prepareRecycler() {
            favoritesAdapter = MealsAdapter()
            binding.rvFavorites.apply{
                layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
                adapter = favoritesAdapter
        }

        private fun observeFavorites() {
            viewModel.observeFavoritesMealsLiveData().observe(requireActivity(),Observer{ meals->
                favoritesAdapter.differ.submitList(meals)

            })
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            prepareRecycler()
            observeFavorites()

    }

}

}