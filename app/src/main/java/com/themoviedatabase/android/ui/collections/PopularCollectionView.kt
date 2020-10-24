package com.themoviedatabase.android.ui.collections

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.themoviedatabase.android.domain.model.colletions.MDBCollectionCategory


class PopularCollectionView : BaseCollectionView() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadCollection(MDBCollectionCategory.Popular)
    }
    override fun onSelectMovie(movieId: Int) {
        findNavController().navigate(PopularCollectionViewDirections.actionNavigationLatestToNavigationDetail(movieId))
    }

}