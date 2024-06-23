package com.example.cuteanimals.view.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(
    @SerializedName("id") var id: String? = null,
    @SerializedName("url") var url: String? = null
) : Parcelable
