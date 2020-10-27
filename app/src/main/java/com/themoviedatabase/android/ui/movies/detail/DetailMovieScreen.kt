package com.themoviedatabase.android.ui.movies.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.navArgs
import com.themoviedatabase.android.R
import com.themoviedatabase.android.databinding.ScreenDetailBinding
import com.themoviedatabase.android.domain.model.movies.MDBMovie
import com.themoviedatabase.android.presentation.movies.presenter.MovieDetailPresenter
import com.themoviedatabase.android.presentation.movies.view.MovieDetailView
import com.themoviedatabase.core.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
class DetailMovieScreen:  BaseActivity<MovieDetailPresenter>(), MovieDetailView {
    val args by navArgs<DetailMovieScreenArgs>()
    @Inject
    lateinit var presenter: MovieDetailPresenter

    private lateinit var binding: ScreenDetailBinding
        @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ScreenDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initView()
        loadDetail()
    }

    @ExperimentalCoroutinesApi
    private fun loadDetail() {
        presenter.loadDetailMovie(args.id)
    }

    override fun showDetailMovie(movie: MDBMovie) {
       binding.movieDetailTitle.text = movie.title
       binding.movieDetailOverview.text = movie.overview
       val image = if(movie.backdrop_path != null){
           "https://image.tmdb.org/t/p/w500${movie.backdrop_path}"
       } else {
           "https://image.tmdb.org/t/p/w500${movie.poster_path}"
       }
       binding.moviewDetailPoster.setImageURI(image)
       binding.movieDetailGenres.text = getString(R.string.movies_genres, movie.genres.toString())
       binding.movieDetailLanguages.text = getString(R.string.movies_language, movie.spoken_languages.toString())

        binding.movieDetailVotesAverage.text = getString(R.string.movies_vote_average, movie.vote_average.toString())
        binding.movieDetailTotalVotes.text = getString(R.string.movies_vote_total, movie.vote_count.toString())
    }

    override fun initView() {
        presenter.attach(this)
    }

    override fun showLoader(show: Boolean) {
        binding.loader.loadingView.visibility = if(show) View.VISIBLE else View.GONE
    }

    override fun showMessage(message: String) {
        
    }

    override fun showMessage(message: Int) {
        
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}