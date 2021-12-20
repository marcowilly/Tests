package com.example.testwebmotors.models

import com.google.gson.annotations.SerializedName

/**
 * Model class VersionModel
 *
 * @author: Marco Willy
 * @since: 19/12/2021
 */

data class Version(
    @SerializedName("ModelID") var modelId: Int,
    @SerializedName("ID") var id: Int,
    @SerializedName("Name") var name: String
)