package br.com.oaleixo.eleticcarapp.data

import br.com.oaleixo.eleticcarapp.domain.Carro
import retrofit2.Call
import retrofit2.http.GET

interface CarsApi {
    @GET("cars.json")
    fun getAllCars() : Call<List<Carro>>

}