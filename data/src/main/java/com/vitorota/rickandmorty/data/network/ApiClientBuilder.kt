package com.vitorota.rickandmorty.data.network

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * @author Vitor Ota
 * @since 22/01/2019
 */
class ApiClientBuilder {

    companion object {
        private val defaultClient: OkHttpClient = OkHttpClient()

        /**
         * @param cacheConfig if null, no cache will be saved
         * */
        fun <S> createServiceApi(
            serviceClass: Class<S>,
            baseUrl: String,
            cacheConfig: CacheConfig? = null,
            gson: Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create(),
            vararg interceptors: Interceptor
        ): S {
            val clientBuilder = defaultClient.newBuilder()
            if (cacheConfig != null) {
                clientBuilder.cache(Cache(cacheConfig.context.cacheDir, cacheConfig.cacheSize))
                clientBuilder.addInterceptor(CacheInterceptor(cacheConfig.context))
            }

            interceptors.forEach { clientBuilder.addInterceptor(it) }

            val client = clientBuilder.build()

            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

            return retrofit.create(serviceClass)
        }
    }

    /**
     * @param cacheSize should be informed in Bytes.
     * */
    data class CacheConfig(val context: Context, val cacheSize: Long)
}