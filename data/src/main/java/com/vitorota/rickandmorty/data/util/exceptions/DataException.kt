package com.vitorota.rickandmorty.data.util.exceptions

import java.lang.Exception

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */
abstract class DataException : Exception {
    constructor(message: String?): super(message)
    constructor(message: String?, cause: Throwable): super(message, cause)
}