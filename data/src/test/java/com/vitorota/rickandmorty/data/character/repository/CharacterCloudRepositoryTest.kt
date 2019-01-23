package com.vitorota.rickandmorty.data.character.repository

import com.vitorota.rickandmorty.data.network.ApiClientBuilder
import com.vitorota.rickandmorty.data.network.api.CharacterApi
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 *
 * @author Vitor Ota
 * @since 23/01/2019
 */
class CharacterCloudRepositoryTest {
    private lateinit var server: MockWebServer
    private lateinit var api: CharacterApi
    private lateinit var repo: CharacterCloudRepository


    @Before
    fun setup() {
        server = MockWebServer()
        server.start()

        val url = server.url("/").toString()

        api = ApiClientBuilder.createServiceApi(CharacterApi::class.java, url)
        repo = CharacterCloudRepository(api)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `get character by id (1) successfully`(){
        TODO()
    }

    @Test
    fun `get inexistent character by id (900) should throw exception`(){
        TODO()
    }

    @Test
    fun `list characters from page (1) successfully`(){
        TODO()
    }

    @Test
    fun `list characters from inexistent page (900) should throw exception`(){
        TODO()
    }



}