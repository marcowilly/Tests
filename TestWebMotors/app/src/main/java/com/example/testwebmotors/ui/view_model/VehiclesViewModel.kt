package com.example.testwebmotors.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.testwebmotors.api.VehicleApi
import com.example.testwebmotors.ui.data_source.VehiclesDataSource

/**
 * ViewModel class to Vehicles
 *
 * @author: Marco Willy
 * @since: 20/12/2021
 **/

class VehiclesViewModel(
    private val api: VehicleApi
) : ViewModel() {
    val vehicles =
        Pager(config = PagingConfig(pageSize = 10, prefetchDistance = 2), pagingSourceFactory = {
            VehiclesDataSource(api)
        }).flow.cachedIn(viewModelScope)
}
