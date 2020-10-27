package com.themoviedatabase.android.presentation.collections.view

import com.themoviedatabase.android.ui.collections.model.MDBItemCollection
import com.themoviedatabase.core.presentation.contracts.BaseViewContract

interface CollectionView: BaseViewContract {
    fun showCollectionMovies(collection: List<MDBItemCollection>)
    fun showRetry()
}