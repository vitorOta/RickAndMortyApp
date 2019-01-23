package com.vitorota.rickandmorty.domain.episode

import com.vitorota.rickandmorty.data.episode.repository.EpisodeRepository
import com.vitorota.rickandmorty.data.episode.usecase.GetEpisodeUseCase
import com.vitorota.rickandmorty.domain.mockEpisodes
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
class GetEpisodeUseCaseTest {


    @MockK
    private lateinit var repo: EpisodeRepository

    private lateinit var useCase: GetEpisodeUseCase
    private var data = mockEpisodes

    @Before
    fun setup() {

        MockKAnnotations.init(this)

        val slot = slot<Int>()

        every {
            runBlocking { repo.get(capture(slot)) }
        } answers {
            data.find { item -> item.id == slot.captured }
        }

        useCase = GetEpisodeUseCase(repo)
    }

    @Test
    fun `get existent episode details successfully`() = runBlocking {
        val id = 1

        val episode = useCase.execute(GetEpisodeUseCase.Params(id))
        assertSame(episode, data[0])
    }

    @Test
    fun `get inexistent episode, should throw exception`() = runBlocking {
        val id = 10
        val episode = useCase.execute(GetEpisodeUseCase.Params(id))
        assertNull(episode)
    }

}