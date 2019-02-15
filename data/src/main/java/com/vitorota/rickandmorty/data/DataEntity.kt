package com.vitorota.rickandmorty.data

/**
 *
 * @author Vitor Ota
 * @since 14/02/2019
 */
interface DataEntity<T> {
    fun toDomain(): T
}