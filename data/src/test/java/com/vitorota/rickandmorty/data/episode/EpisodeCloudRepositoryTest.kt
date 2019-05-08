package com.vitorota.rickandmorty.data.character.repository

import com.vitorota.rickandmorty.data.episode.repository.EpisodeCloudRepository
import com.vitorota.rickandmorty.data.network.ApiClientBuilder
import com.vitorota.rickandmorty.data.network.RickAndMortyApi
import com.vitorota.rickandmorty.data.util.enqueueResponse
import com.vitorota.rickandmorty.data.util.exceptions.DataHttpException
import com.vitorota.rickandmorty.data.util.loadJsonFromResources
import com.vitorota.rickandmorty.data.util.toDomain
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 *
 * @author Vitor Ota
 * @since 23/01/2019
 */
class EpisodeCloudRepositoryTest {
    private lateinit var server: MockWebServer
    private lateinit var api: RickAndMortyApi
    private lateinit var repo: EpisodeCloudRepository

    private val filePrefix = "episode"

    @Before
    fun setup() {
        server = MockWebServer()
        server.start()

        val url = server.url("/").toString()

        api = ApiClientBuilder.createServiceApi(RickAndMortyApi::class.java, url)
        repo = EpisodeCloudRepository(api)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `get item by id (1) successfully`() = runBlocking {

        //region arrange
        val id = 1
        val response = loadJsonFromResources("${filePrefix}_get_$id.json")
        server.enqueueResponse(200, response)
        //endregion

        //region act
        val item = repo.get(id)
        //endregion

        //region assert
        assertNotNull(item)
        assertEquals(id, item?.id)
        assertEquals("Pilot", item?.name)
        //endregion
    }

    @Test
    fun `get inexistent item by id (99999) should throw DataHttpException null and exception should have statusCode = 404`() =
        runBlocking {
        //region arrange
        val id = 99999
            server.enqueueResponse(404, "{\"error\":\"Episode not found\"}")
        //endregion

        try {
            //region act
            repo.get(id)
            fail()
            //endregion
        } catch (e: DataHttpException) {
            //region assert
            assertEquals(404, e.statusCode)
            //endregion
        }
    }

    @Test
    fun `list response from page (1) successfully`() = runBlocking {
        //region arrange
        val page = 1
        val response = loadJsonFromResources("${filePrefix}_list_$page.json")
        server.enqueueResponse(200, response)
        //endregion


        //region act
        val listResponse = repo.listResponse(page)
        //endregion

        //region assert
        assertEquals("Pilot", listResponse.results.toDomain()[0].name)
        assertEquals(31, listResponse.info.count)
        assertEquals("https://rickandmortyapi.com/api/episode?page=2", listResponse.info.next)
        //endregion
    }

    @Test
    fun `list from inexistent page (99999) should throw DataHttpException`() = runBlocking {
        //region arrange
        val page = 99999
        server.enqueueResponse(404, "{\"error\":\"There is nothing here\"}")
        //endregion

        try {
            //region act
            repo.list(page)
            fail()
            //endregion
        } catch (e: DataHttpException) {
            //region assert
            assertEquals(404, e.statusCode)
            //endregion
        }
    }


}