package com.vitorota.rickandmorty.domain.character

import com.vitorota.rickandmorty.data.character.repository.CharacterRepository
import com.vitorota.rickandmorty.data.character.usecase.GetCharacterUseCase
import com.vitorota.rickandmorty.domain.mockCharacters
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
class GetCharacterUseCaseTest {

    private lateinit var repository: CharacterRepository

    private lateinit var useCase: GetCharacterUseCase

    private val data = mockCharacters

    @Before
    fun setup() {

        repository = mockk()

        val slot = slot<Int>()

        every {
            repository.get(capture(slot))
        } answers {
            data.find { item -> item.id == slot.captured }
        }

        useCase = GetCharacterUseCase(repository)
    }

    @Test
    fun `get existent character details successfully`() {
        val id = 1

        val character = useCase.execute(GetCharacterUseCase.Params(id))
        assertSame(character, data[0])
    }

    @Test
    fun `get inexistent character, should throw exception`() {
        val id = 10
        val character = useCase.execute(GetCharacterUseCase.Params(id))
        assertNull(character)
    }
}