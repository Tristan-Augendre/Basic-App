package com.evendred.basicapp.ui.main

import com.evendred.basicapp.actor.okhttp.OkHttpModule
import dagger.Component

@Component(modules = [OkHttpModule::class, MainViewModel.MainViewModelModule::class])
interface MainFragmentComponent {
    fun inject(fragment: MainFragment)
}