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
         * @param cacheSize should be informed in Bytes. if null, no cache will be saved
         * */
        fun <S> createServiceApi(
            serviceClass: Class<S>,
            context: Context,
            baseUrl: String,
            cacheSize: Long? = null,
            gson: Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create(),
            vararg interceptors: Interceptor
        ): S {
            val clientBuilder = defaultClient.newBuilder()
            if (cacheSize != null) {
                clientBuilder.cache(Cache(context.cacheDir, cacheSize))
                clientBuilder.addInterceptor(CacheInterceptor(context))
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

}