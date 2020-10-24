package com.themoviedatabase.android.data.collections.movies.datasource

import com.themoviedatabase.android.data.model.movies.MoviesCollectionDto
import com.themoviedatabase.android.data.model.movies.MovieDto
import com.themoviedatabase.android.domain.model.colletions.MDBCollection
import javax.inject.Inject

class MapperMovieCollection @Inject constructor() {

    fun dtoToDomain(item: MoviesCollectionDto): MDBCollection {
        return MDBCollection(item.adult, item.backdrop_path?:"",
                item.genre_ids?.map {
                "" },
                item.id, item.original_language, item.original_title, item.overview, item.popularity, item.poster_path, item.release_date, item.title, item.video, item.vote_average, item.vote_count)
    }

    fun dtoToDomain(item: MovieDto): MDBCollection {
        return MDBCollection(item.adult, item.backdrop_path
                ?:"", item.genres?.map {
            it.name
        }, item.id, item.original_language, item.original_title, item.overview, item.popularity, item.poster_path?:"", item.release_date, item.title, item.video, item.vote_average, item.vote_count)
    }

}