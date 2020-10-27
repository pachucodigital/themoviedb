package com.themoviedatabase.android.ui.collections

import androidx.navigation.fragment.findNavController
import com.themoviedatabase.android.domain.model.colletions.MDBCollectionCategory
import kotlinx.coroutines.ExperimentalCoroutinesApi


class PopularCollectionView : BaseCollectionView() {

    @ExperimentalCoroutinesApi
    override fun loadCollection() {
        presenter.loadCollection(MDBCollectionCategory.Popular)
    }
    override fun onSelectMovie(movieId: Int) {
        findNavController().navigate(PopularCollectionViewDirections.actionNavigationLatestToNavigationDetail(movieId))
    }

}