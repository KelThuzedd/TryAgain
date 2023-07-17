package com.example.tryagain

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("id") val id: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("age_group") val child: String = "",
    @SerializedName("image_name") val imageName: String = ""
)