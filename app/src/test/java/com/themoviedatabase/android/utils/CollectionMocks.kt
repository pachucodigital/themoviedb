package com.themoviedatabase.android.utils

import com.themoviedatabase.android.domain.model.colletions.MDBCollection
import com.themoviedatabase.android.ui.collections.model.MDBItemCollection

object CollectionMocks {

    private fun getMockCollection(): MDBCollection {
        return MDBCollection(true, "", arrayListOf("Terror"), 100, "eng", "Testing title movie", "Testing",5.0,"","01/01/2020",
            "Title", false, 5.0, 100)
    }

    fun getMockListCollection(): List<MDBCollection> {
        return arrayListOf(getMockCollection())
    }
    fun getMockItemCollection(): List<MDBItemCollection> {
        val collection  = getMockCollection()
        return arrayListOf(MDBItemCollection(collection.id, collection.title, collection.overview, collection.poster_path, collection.toString()))
    }
}
