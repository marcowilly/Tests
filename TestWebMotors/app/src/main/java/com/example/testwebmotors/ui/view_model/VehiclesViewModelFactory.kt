package com.example.testwebmotors.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testwebmotors.api.VehicleApi

/**
 * ViewModel Factory class to Vehicles
 *
 * @author: Marco Willy
 * @since: 20/12/2021
 **/

class VehiclesViewModelFactory(
    private val api: VehicleApi
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VehiclesViewModel(api) as T
    }
}