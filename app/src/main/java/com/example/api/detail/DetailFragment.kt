package com.example.api.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.api.R
import com.example.api.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater)
        binding.setLifecycleOwner (this)

        //eccept args
        var argUsername = DetailFragmentArgs.fromBundle(arguments!!).username
        val vmFactory = DetailViewModelFactory(argUsername)

        binding.viewModel = ViewModelProvider(this, vmFactory).get(DetailViewModel::class.java)

        return binding.root
    }
}