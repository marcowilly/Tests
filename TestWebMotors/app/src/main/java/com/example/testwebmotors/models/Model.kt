package com.example.testwebmotors.models

import com.google.gson.annotations.SerializedName

/**
 * Model class MakeId
 *
 * @author: Marco Willy
 * @since: 19/12/2021
 */

data class Model(
    @SerializedName("MakeID") var makeId: Int,
    @SerializedName("ID") var id: Int,
    @SerializedName("Name") var name: String
)