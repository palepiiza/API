package com.example.api

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.api.databinding.FragmentOverviewBinding


class OverviewFragment : Fragment() {
    private lateinit var viewModel: OverviewViewModel
    private lateinit var binding: FragmentOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)
        binding = FragmentOverviewBinding.inflate(inflater)

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

            val viewAdapter = ItemAdapter({item->showDetail(item)})
            binding.recyclerView.adapter = viewAdapter

        viewModel.items.observe(this, Observer { list->
            viewAdapter.submitList(list)
        })

        return binding.root
    }
    fun showDetail(username: String){
        Log.d("debug", "OnClick : " + username)
        this.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(username))
    }
}