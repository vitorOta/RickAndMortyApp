package com.vitorota.rickandmorty.data

/**
 *
 * @author Vitor Ota
 * @since 17/01/2019
 */
interface Repository<T:BaseEntity> {
    fun list(page: Int = 1): List<T>
    fun get(id: Int): T?
}