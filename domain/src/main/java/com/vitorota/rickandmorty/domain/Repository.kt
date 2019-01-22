package com.vitorota.rickandmorty.data

/**
 *
 * @author Vitor Ota
 * @since 17/01/2019
 */
interface Repository<T> {
    suspend fun list(page: Int = 1): List<T>
    suspend fun get(id: Int): T?
}