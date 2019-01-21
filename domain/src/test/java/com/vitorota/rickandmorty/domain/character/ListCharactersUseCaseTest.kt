package com.vitorota.rickandmorty.domain.character

import com.vitorota.rickandmorty.data.character.repository.CharacterRepository
import com.vitorota.rickandmorty.data.character.usecase.ListCharactersUseCase
import com.vitorota.rickandmorty.domain.getItemsPage
import com.vitorota.rickandmorty.domain.mockCharacters
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 *
 * @author Vitor Ota
 * @since 21/01/2019
 */
class ListCharactersUseCaseTest {

    private lateinit var repository: CharacterRepository
    private lateinit var useCase: ListCharactersUseCase
    private val data = mockCharacters

    @Before
    fun setup() {
        repository = mockk()

        val slot = slot<Int>()


        every {
            repository.list(capture(slot))
        } answers {
            data.getItemsPage(slot.captured)
        }

        useCase = ListCharactersUseCase(repository)
    }

    @Test
    fun `list first page of characters`() {
        val characters = useCase.execute(ListCharactersUseCase.Params())
        Assert.assertEquals(characters, data.getItemsPage(1))
    }

    @Test
    fun `list second page of characters`() {
        val page = 2
        val characters = useCase.execute(ListCharactersUseCase.Params(page))
        Assert.assertEquals(characters, data.getItemsPage(page))
        assert(characters.size == 2)
    }

    @Test
    fun `list a page of characters that not exists, should throw exception`() {
        val page = 10
        try {
            val characters = useCase.execute(ListCharactersUseCase.Params(page))
            Assert.fail()
        } catch (e: IllegalArgumentException) {
        }

    }


}