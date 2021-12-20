package com.example.testwebmotors.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Model class Vehicle
 *
 * @author: Marco Willy
 * @since: 19/12/2021
 */

data class Vehicle(
    @SerializedName("ID") var id: Int,
    @SerializedName("Make") var make: String,
    @SerializedName("Model") var model: String,
    @SerializedName("Version") var version: String,
    @SerializedName("Image") var image: String,
    @SerializedName("KM") var km: Int,
    @SerializedName("Price") var price: String,
    @SerializedName("YearModel") var yearModel: Int,
    @SerializedName("YearFab") var yearFab: Int,
    @SerializedName("Color") var color: String
) : Serializable