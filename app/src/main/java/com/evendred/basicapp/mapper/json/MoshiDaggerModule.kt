package com.evendred.basicapp.mapper.json

import com.evendred.basicapp.mapper.json.template.JsonChampionsList
import com.evendred.basicapp.model.Todo
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.Module
import dagger.Provides

@Module
class MoshiDaggerModule {
    @Provides
    fun provideMoshiClient(): Moshi = Moshi.Builder().build()

    @Provides
    fun provideTodoListJsonAdapter(moshi: Moshi): JsonAdapter<List<Todo>> {
        val type = Types.newParameterizedType(List::class.java, Todo::class.java)
        return moshi.adapter(type)
    }

    @Provides
    fun provideChampionsListJsonAdapter(moshi: Moshi): JsonAdapter<JsonChampionsList> {
        return moshi.adapter(JsonChampionsList::class.java)
    }
}