package com.example.proyecto_a.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.proyecto_a.R

class CategoriesFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        return inflater.inflate(R.layout.fragment_categories, container, false)
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
