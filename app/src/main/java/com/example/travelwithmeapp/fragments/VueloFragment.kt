package com.example.travelwithmeapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.travelwithmeapp.databinding.FragmentVueloBinding


class VueloFragment : Fragment() {
    private lateinit var binding: FragmentVueloBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVueloBinding.inflate(inflater, container, false)
        return binding.root

    }

}