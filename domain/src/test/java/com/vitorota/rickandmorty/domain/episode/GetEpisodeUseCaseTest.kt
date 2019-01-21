package com.vitorota.rickandmorty.domain.episode

import com.vitorota.rickandmorty.data.episode.repository.EpisodeRepository
import com.vitorota.rickandmorty.data.episode.usecase.GetEpisodeUseCase
import com.vitorota.rickandmorty.domain.mockEpisodes
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertSame
import org.junit.Before
import org.junit.Test

/**
 *
 * @author Vitor Ota
 * @since 21/01/2019
 */
class GetEpisodeUseCaseTest {

    private lateinit var repo: EpisodeRepository
    private lateinit var useCase: GetEpisodeUseCase
    private var data = mockEpisodes

    @Before
    fun setup() {
        repo = mockk()
        val slot = slot<Int>()

        every {
            repo.get(capture(slot))
        } answers {
            data.find { item -> item.id == slot.captured }
        }

        useCase = GetEpisodeUseCase(repo)
    }

    @Test
    fun `get existent episode details successfully`() {
        val id = 1

        val episode = useCase.execute(GetEpisodeUseCase.Params(id))
        assertSame(episode, data[0])
    }

    @Test
    fun `get inexistent episode, should throw exception`() {
        val id = 10
        val episode = useCase.execute(GetEpisodeUseCase.Params(id))
        assertNull(episode)
    }

}