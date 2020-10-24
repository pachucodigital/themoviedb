package com.themoviedatabase.android.domain.model.colletions

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.lang.StringBuilder

@Serializable
data class MDBCollection(
        @SerialName("adult") val adult: Boolean,
         @SerialName("backdrop_path") val backdrop_path: String?,
        @SerialName("genres") val genres: List<String>? = null,
        @SerialName("id") val id: Int,
        @SerialName("original_language") val original_language: String,
        @SerialName("original_title") val original_title: String,
        @SerialName("overview") val overview: String,
        @SerialName("popularity") val popularity: Double,
        @SerialName("poster_path") val poster_path: String,
        @SerialName("release_date") val release_date: String,
        @SerialName("title") val title: String,
        @SerialName("video") val video: Boolean,
        @SerialName("vote_average") val vote_average: Double,
        @SerialName("vote_count") val vote_count: Int
){
    override fun toString(): String {
      return "$release_date / ${getGenres()} / $original_language"
    }

    private fun getGenres(): String {
        return if(genres != null && genres.isNotEmpty()) {
            val builder= StringBuilder()
            for(g:String in genres){
                if(g.isNotBlank() && g.isNotEmpty()){
                    builder.append(g)
                }
            }
            builder.toString()
        }else {
            ""
        }
    }
}
