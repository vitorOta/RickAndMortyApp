package com.vitorota.rickandmorty.domain.location

import com.vitorota.rickandmorty.data.location.repository.LocationRepository
import com.vitorota.rickandmorty.data.location.usecase.GetLocationUseCase
import com.vitorota.rickandmorty.domain.mockLocations
import io.mockk.*
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertSame
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 *
 * @author Vitor Ota
 * @since 21/01/2019
 */
class GetLocationUseCaseTest {
    @MockK
    private lateinit var repo: LocationRepository

    private lateinit var useCase: GetLocationUseCase

    private val data = mockLocations

    @Before
    fun setup() {

        MockKAnnotations.init(this)

        val slot = slot<Int>()
        every() {
            runBlocking { repo.get(capture(slot)) }
        } answers { data.find { item -> item.id == slot.captured } }

        useCase = GetLocationUseCase(repo)
    }

    @Test
    fun `get existent location details successfully`() = runBlocking {
        val id = 1

        val episode = useCase.execute(GetLocationUseCase.Params(id))
        assertSame(episode, data[0])
    }

    @Test
    fun `get inexistent location, should throw exception`()  = runBlocking{
        val id = 10
        val episode = useCase.execute(GetLocationUseCase.Params(id))
        assertNull(episode)
    }

}