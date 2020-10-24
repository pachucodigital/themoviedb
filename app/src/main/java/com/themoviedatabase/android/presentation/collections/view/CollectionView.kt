package com.themoviedatabase.android.presentation.collections.view

import com.themoviedatabase.android.ui.collections.model.MDBCollection
import com.themoviedatabase.core.presentation.contracts.BaseViewContract

interface CollectionView: BaseViewContract {
    fun showCollectionMovies(collection: List<MDBCollection>)
}