package com.themoviedatabase.android.ui.home.movies.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class MDBCollection (@SerialName("id") val id: Int,
                          @SerialName("title") val title: String,
                          @SerialName("overview") val overview: String,
                          @SerialName("poster_path") val poster_path: String?,
                          @SerialName("extras") val extras: String?,

                          )