package com.example.testwebmotors.ui.data_source

import android.os.StrictMode
import androidx.paging.PagingSource
import com.example.testwebmotors.api.VehicleApi
import com.example.testwebmotors.models.Vehicle
import retrofit2.Call
import retrofit2.Response

/**
 * DataSource class to Vehicles
 *
 * @author: Marco Willy
 * @since: 20/12/2021
 **/

class VehiclesDataSource(private val api: VehicleApi) : PagingSource<Int, Vehicle>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Vehicle> {
        return try {
            val nextPageNumber = params.key ?: 1
            val callback: Call<List<Vehicle>> = api.getVehicles(nextPageNumber)
            StrictMode
                .setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
            val response: Response<List<Vehicle>> = callback.execute()
            val hasItem: Boolean = response.body()!!.isNotEmpty()

            LoadResult.Page(
                data = response.body()!!,
                prevKey = if (nextPageNumber > 1) nextPageNumber - 1 else null,
                nextKey = if (hasItem) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}