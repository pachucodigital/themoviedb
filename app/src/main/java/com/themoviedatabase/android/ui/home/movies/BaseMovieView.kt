package com.themoviedatabase.android.ui.home.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.themoviedatabase.android.databinding.FragmentMoviesBinding
import com.themoviedatabase.android.domain.model.movies.MDBMoviesCategory
import com.themoviedatabase.android.presentation.movies.presenter.MoviePresenter
import com.themoviedatabase.android.presentation.movies.view.MovieView
import com.themoviedatabase.android.ui.home.movies.adapter.MoviesCollectionAdapter
import com.themoviedatabase.android.ui.home.movies.model.MDBCollection
import com.themoviedatabase.core.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
@AndroidEntryPoint
abstract class BaseMovieView : BaseFragment<MoviePresenter>(), MovieView {
    @Inject
    lateinit var presenter: MoviePresenter

    protected var binding: FragmentMoviesBinding? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        initView()
        return binding?.root
    }

    @ExperimentalCoroutinesApi
    protected fun loadCollection(category: MDBMoviesCategory) {
        presenter.loadCollection(category)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        presenter.detach()
    }

    override fun initView() {
        binding?.moviesRecyclerview?.layoutManager = LinearLayoutManager(context)
        presenter.attach(this)
    }

    override fun showLoader(show: Boolean) {
        binding?.loader?.loadingView?.visibility = if(show) View.VISIBLE else View.GONE
    }

    override fun showMessage(message: String) {
        if(binding != null) {
            Snackbar.make(binding?.moviesMainContent!!, message, Snackbar.LENGTH_SHORT).show()
        }

    }

    override fun showMessage(message: Int) {
        if(binding != null) {
            Snackbar.make(binding?.moviesMainContent!!, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun showCollectionMovies(collection: List<MDBCollection>) {
        binding?.moviesRecyclerview?.adapter = MoviesCollectionAdapter(collection, object : MoviesCollectionAdapter.SelectMovieListener{
                override fun onSelectMovie(movieId: Int) {

            }
        })
    }





}