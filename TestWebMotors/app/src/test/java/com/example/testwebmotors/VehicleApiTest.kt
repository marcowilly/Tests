package com.example.testwebmotors

import com.example.testwebmotors.api.VehicleApi
import org.junit.Test
import retrofit2.HttpException

class VehicleApiTest {
    @Test
    suspend fun getVehicles() {
        val model: String = try {
            val response = VehicleApi.client.getVehicles(page = 1)
            print("RESPONSE: "+response)
            val vehicles = response.results
            vehicles.get(0).model
        } catch (exception: HttpException) {
            "ERROR"
        }
        print("TESTE: "+model)
        assert(model == "ERROR")
    }
}