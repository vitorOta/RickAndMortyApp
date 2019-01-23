package com.vitorota.rickandmorty.data.util.exceptions

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */
class DataHttpException(message: String?, var statusCode: Int, cause:Throwable?) : DataException(message,cause) {
}