package com.mo.pokeapp.network

import com.mo.pokeapp.BuildConfig
import com.mo.pokeapp.data.dto.SpeciesListResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("pokemon-species")
    suspend fun getSpecies(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): SpeciesListResponse

    companion object {
        fun create(): APIService {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkHttpClient())
                .baseUrl(BuildConfig.BASE_URL)
                .build()
                .create(APIService::class.java)
        }

        private fun provideOkHttpClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()
            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY

            if (BuildConfig.DEBUG) {
                builder.addInterceptor(logInterceptor)
            }

            return builder.build()
        }

        fun photoUrl(id: Int): String =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
    }
}