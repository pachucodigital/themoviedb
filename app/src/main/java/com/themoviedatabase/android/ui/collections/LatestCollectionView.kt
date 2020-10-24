package com.themoviedatabase.android.ui.collections

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.themoviedatabase.android.domain.model.colletions.MDBCollectionCategory
import kotlinx.coroutines.ExperimentalCoroutinesApi


class LatestCollectionView : BaseCollectionView() {


    @ExperimentalCoroutinesApi
    override fun loadCollection() {
        presenter.loadCollection(MDBCollectionCategory.Latest)
    }

    override fun onSelectMovie(movieId: Int) {
        findNavController().navigate(
            LatestCollectionViewDirections.actionNavigationLatestToNavigationDetail(
                movieId
            )
        )
    }
}