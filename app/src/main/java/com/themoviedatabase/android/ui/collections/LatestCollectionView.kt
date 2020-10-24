package com.themoviedatabase.android.ui.collections

import androidx.navigation.fragment.findNavController
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