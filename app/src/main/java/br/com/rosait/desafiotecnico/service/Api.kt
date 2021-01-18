package br.com.rosait.desafiotecnico.service

import br.com.rosait.desafiotecnico.model.Result
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("sandbox/products")
    fun getResult() : Call<Result>
}