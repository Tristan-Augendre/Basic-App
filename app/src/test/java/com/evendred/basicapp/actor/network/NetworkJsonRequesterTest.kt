package com.evendred.basicapp.actor.network

import com.evendred.basicapp.extensions.mock
import com.evendred.basicapp.mapper.json.JsonMapper
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito.`when`

class NetworkJsonRequesterTest {
    private val stringRequester: NetworkStringRequester = mock()
    private val jsonMapper: JsonMapper<Any> = mock()

    private fun setup(): NetworkJsonRequester<Any> = NetworkJsonRequester(stringRequester, jsonMapper)

    @Test
    fun getJson() = runBlocking {
        //GIVEN
        val requester = setup()

        val jsonResult = Any()
        `when`(stringRequester.getString("url")).thenReturn("response")
        `when`(jsonMapper.map("response")).thenReturn(jsonResult)

        //WHEN
        val json = requester.getJson("url")

        //THEN
        assertEquals(jsonResult, json)
    }
}