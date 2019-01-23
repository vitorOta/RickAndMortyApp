package com.vitorota.rickandmorty.domain.character

import com.vitorota.rickandmorty.data.character.repository.CharacterRepository
import com.vitorota.rickandmorty.data.character.usecase.GetCharacterUseCase
import com.vitorota.rickandmorty.domain.mockCharacters
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.slot
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
class GetCharacterUseCaseTest {

    @MockK
    private lateinit var repository: CharacterRepository

    private lateinit var useCase: GetCharacterUseCase

    private val data = mockCharacters

    @Before
    fun setup() {

        MockKAnnotations.init(this)

        val slot = slot<Int>()

        every {
            runBlocking { repository.get(capture(slot)) }
        } answers {
            data.find { item -> item.id == slot.captured }
        }

        useCase = GetCharacterUseCase(repository)
    }

    @Test
    fun `get existent character details successfully`() = runBlocking {
        val id = 1

        val character = useCase.execute(GetCharacterUseCase.Params(id))
        assertSame(character, data[0])
    }

    @Test
    fun `get inexistent character, should throw exception`() = runBlocking {
        val id = 10
        val character = useCase.execute(GetCharacterUseCase.Params(id))
        assertNull(character)
    }
}