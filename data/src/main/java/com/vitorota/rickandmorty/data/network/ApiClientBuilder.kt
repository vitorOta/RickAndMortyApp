package com.vitorota.rickandmorty.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
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
        private lateinit var retrofit: Retrofit
        private lateinit var clientBuilder: OkHttpClient.Builder

        fun <S> createServiceApi(
            serviceClass: Class<S>,
            baseUrl: String,
            gson: Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create(),
            vararg interceptors: Interceptor
        ): S {
            clientBuilder = OkHttpClient.Builder()

            interceptors.forEach { clientBuilder.addInterceptor(it) }


            val client = clientBuilder.build()
            retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

            return retrofit.create(serviceClass)
        }

    }

}