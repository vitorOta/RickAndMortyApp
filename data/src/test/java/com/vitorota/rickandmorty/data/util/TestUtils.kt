package com.vitorota.rickandmorty.data.util

import java.io.File


/**
 *
 * @author Vitor Ota
 * @since 23/01/2019
 */

@Throws(IllegalStateException::class)
fun Any.loadJsonFromResources(fileName : String) : String {
    // Load the JSON response
    val uri = this.javaClass.classLoader.getResource("json/$fileName")
    val file = File(uri.path)
    return String(file.readBytes())
}