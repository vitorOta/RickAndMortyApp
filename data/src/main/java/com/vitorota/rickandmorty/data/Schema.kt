package com.vitorota.rickandmorty.data

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */
interface Schema<T> {

    fun toDomain():T
}