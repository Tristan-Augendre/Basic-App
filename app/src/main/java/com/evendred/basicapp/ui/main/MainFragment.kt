package com.evendred.basicapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.evendred.basicapp.R
import com.evendred.basicapp.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment: Fragment() {
    @Inject lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        MainDaggerInjection.injectMainFragment(this, savedInstanceState)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editText.doAfterTextChanged { viewModel.onEditTextChanged(it) }
        button.setOnClickListener { viewModel.onClick() }
    }
}