package com.example.testwebmotors.api

import com.example.testwebmotors.models.Vehicle
import com.example.testwebmotors.utils.Constants.Retrofit.Companion.BASE_URL
import com.example.testwebmotors.utils.Constants.Retrofit.Companion.TIMEOUT_CONECTION
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * Api interface Vehicle
 *
 * @author: Marco Willy
 * @since: 19/12/2021
 **/

interface VehicleApi {

    @GET("Vehicles")
    fun getVehicles(@Query("Page") page: Int): Call<List<Vehicle>>

    companion object {

        operator fun invoke(): VehicleApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().also { client ->
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
                client.readTimeout(TIMEOUT_CONECTION.toLong(), TimeUnit.SECONDS)
                client.connectTimeout(TIMEOUT_CONECTION.toLong(), TimeUnit.SECONDS)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VehicleApi::class.java)
    }
}