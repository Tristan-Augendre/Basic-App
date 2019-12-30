package com.evendred.basicapp.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get
import androidx.savedstate.SavedStateRegistryOwner
import com.evendred.basicapp.actor.network.OkHttpDaggerModule
import com.evendred.basicapp.mapper.json.MoshiDaggerModule
import dagger.Component
import dagger.Module
import dagger.Provides

class MainDaggerInjection {
    companion object {
        fun injectMainFragment(fragment: MainFragment, savedInstanceState: Bundle?) = DaggerMainDaggerInjection_MainFragmentComponent.builder()
            .mainViewModelFactoryModule(MainViewModelFactoryModule(fragment, savedInstanceState))
            .mainViewModelModule(MainViewModelModule(fragment))
            .build()
            .inject(fragment)
    }

    @Component(modules = [OkHttpDaggerModule::class, MoshiDaggerModule::class, MainViewModelModule::class, MainViewModelFactoryModule::class, MainModelModule::class])
    interface MainFragmentComponent {
        fun inject(fragment: MainFragment)
    }

    @Module
    class MainViewModelModule(private val owner: ViewModelStoreOwner) {
        @Provides
        fun provideMainViewModel(factory: MainViewModel.Factory): MainViewModel {
            return ViewModelProvider(owner, factory).get()
        }
    }

    @Module
    class MainViewModelFactoryModule(private val owner: SavedStateRegistryOwner, private val defaultArgs: Bundle?) {
        @Provides
        fun provideMainViewModelFactory(controller: MainController): MainViewModel.Factory {
            return MainViewModel.Factory(owner, defaultArgs, controller)
        }
    }

    @Module
    class MainModelModule {
        @Provides
        fun provideMainModel(): MainModel {
            return MainModel("")
        }
    }
}