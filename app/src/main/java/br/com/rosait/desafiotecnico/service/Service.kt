package br.com.rosait.desafiotecnico.service

import br.com.rosait.desafiotecnico.model.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Service {

    fun getResult(callback:(Result?) -> Unit) {
        val endpoint = RestManager.getEndpoint()

        val call = endpoint.getResult()

        call.enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if(response.isSuccessful) {
                    response.body()?.let {
                        callback(it)
                    }
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                callback(null)
            }
        })
    }
}