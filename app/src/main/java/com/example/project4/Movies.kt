package com.example.project4

import com.google.gson.annotations.SerializedName


class Movies {



    @JvmField
    @SerializedName("original_name")
    var title: String? = null



    @SerializedName("poster_path")
    var poster: String? = null


    @SerializedName("overview")
    var description: String? = null






}