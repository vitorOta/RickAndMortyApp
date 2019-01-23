package com.vitorota.rickandmorty.data.util.exceptions

import java.lang.Exception

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */
abstract class DataException(message:String?, cause:Throwable?=null) : Exception(message, cause) {
}