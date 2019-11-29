package com.evendred.basicapp.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.evendred.basicapp.R
import com.evendred.basicapp.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment: Fragment() {
    private lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this, MainViewModel.Factory()).get()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener { viewModel.onClick() }
    }
}