package com.evendred.basicapp.ui.champion.list

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get
import com.evendred.basicapp.actor.network.OkHttpDaggerModule
import com.evendred.basicapp.mapper.Mapper
import com.evendred.basicapp.mapper.json.template.JsonChampionsList
import com.evendred.basicapp.mapper.json.MoshiDaggerModule
import dagger.Component
import dagger.Module
import dagger.Provides

class ChampionsListDaggerInjection {
    companion object {
        fun injectChampionsListFragment(fragment: ChampionsListFragment) = DaggerChampionsListDaggerInjection_ChampionsListFragmentComponent.builder()
            .championsListModule(ChampionsListModule(fragment))
            .build()
            .inject(fragment)
    }

    @Component(modules = [OkHttpDaggerModule::class, MoshiDaggerModule::class, ChampionsListModule::class])
    interface ChampionsListFragmentComponent {
        fun inject(fragment: ChampionsListFragment)
    }

    @Module
    class ChampionsListModule(private val owner: ViewModelStoreOwner) {
        @Provides
        fun provideViewModel(factory: ChampionsListViewModel.Factory): ChampionsListViewModel {
            return ViewModelProvider(owner, factory).get()
        }

        @Provides
        fun provideViewModelFactory(controller: ChampionsListController): ChampionsListViewModel.Factory {
            return ChampionsListViewModel.Factory(controller)
        }

        @Provides
        fun provideModel(): ChampionsListModel {
            return ChampionsListModel(emptyList())
        }

        @Provides
        fun provideChampionsListMapper(): Mapper<JsonChampionsList, List<Champion>> {
            return ChampionsListMapper()
        }
    }
}