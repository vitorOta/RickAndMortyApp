package com.vitorota.rickandmorty.data

/**
 *
 * @author Vitor Ota
 * @since 17/01/2019
 */
interface UseCase<T, Params> {
    suspend fun execute(params: Params): T
}