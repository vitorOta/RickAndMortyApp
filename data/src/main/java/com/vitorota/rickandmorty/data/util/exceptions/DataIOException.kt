package com.vitorota.rickandmorty.data.util.exceptions

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */
class DataIOException : DataException {
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable) : super(message, cause)
}