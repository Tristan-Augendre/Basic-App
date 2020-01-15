package com.evendred.basicapp.actor.network

import com.evendred.basicapp.extensions.mock
import kotlinx.coroutines.runBlocking
import okhttp3.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito.`when`

class NetworkStringRequesterTest {
    private val httpClient: OkHttpClient = mock()
    private val httpRequestBuilder: Request.Builder = mock()

    private fun setup(): NetworkStringRequester = NetworkStringRequester(httpClient, httpRequestBuilder)

    @Test
    fun getString() = runBlocking {
        //GIVEN
        val requester = setup()

        val requestBuilder: Request.Builder = mock()
        val request: Request = mock()
        val call: Call = mock()
        val response : Response = mock()
        `when`(httpRequestBuilder.url("url")).thenReturn(requestBuilder)
        `when`(requestBuilder.build()).thenReturn(request)
        `when`(httpClient.newCall(request)).thenReturn(call)
        `when`(call.execute()).thenReturn(response)

        `when`(response.isSuccessful).thenReturn(true)

        val responseBody: ResponseBody = mock()
        `when`(response.body).thenReturn(responseBody)
        `when`(responseBody.string()).thenReturn("result")

        //WHEN
        val result = requester.getString("url")

        //THEN
        assertEquals("result", result)
    }

    @Test
    fun getStringWhenNetworkException() = runBlocking<Unit> {
        //GIVEN
        val requester = setup()

        val requestBuilder: Request.Builder = mock()
        val request: Request = mock()
        val call: Call = mock()
        val response : Response = mock()
        `when`(httpRequestBuilder.url("url")).thenReturn(requestBuilder)
        `when`(requestBuilder.build()).thenReturn(request)
        `when`(httpClient.newCall(request)).thenReturn(call)
        `when`(call.execute()).thenReturn(response)

        `when`(response.isSuccessful).thenReturn(false)

        `when`(response.toString()).thenReturn("500")

        //WHEN
        try {
            requester.getString("url")
        }

        //THEN
        catch (e: Exception) {
            assertEquals("Unexpected code 500", e.message)
        }
    }
}