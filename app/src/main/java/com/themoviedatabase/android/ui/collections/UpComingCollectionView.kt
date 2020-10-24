package com.themoviedatabase.android.ui.collections

import androidx.navigation.fragment.findNavController
import com.themoviedatabase.android.domain.model.colletions.MDBCollectionCategory
import kotlinx.coroutines.ExperimentalCoroutinesApi


class UpComingCollectionView : BaseCollectionView() {

    @ExperimentalCoroutinesApi
    override fun loadCollection() {
        presenter.loadCollection(MDBCollectionCategory.UpComing)
    }

    override fun onSelectMovie(movieId: Int) {
        findNavController().navigate(UpComingCollectionViewDirections.actionNavigationLatestToNavigationDetail(movieId))
    }
}