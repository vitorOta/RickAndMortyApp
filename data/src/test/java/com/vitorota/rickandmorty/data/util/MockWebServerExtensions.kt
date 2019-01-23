package com.vitorota.rickandmorty.data.util

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer


/**
 *
 * @author Vitor Ota
 * @since 23/01/2019
 */

fun MockWebServer.enqueueResponse(responseCode: Int, body: String) {
    enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(body)
    )
}