package com.example.testwebmotors.models

import com.google.gson.annotations.SerializedName

/**
 * Model class MakeModel
 *
 * @author: Marco Willy
 * @since: 19/12/2021
 */

data class Make(
    @SerializedName("ID") var id: Int,
    @SerializedName("Name") var name: String
)