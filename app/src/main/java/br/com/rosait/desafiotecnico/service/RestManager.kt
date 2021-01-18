package br.com.rosait.desafiotecnico.service

import br.com.rosait.desafiotecnico.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestManager {

    companion object {
        fun getEndpoint() : Api {

            return  Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}