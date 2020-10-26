package com.themoviedatabase.android.ui.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.themoviedatabase.android.databinding.FragmentCollectionsBinding
import com.themoviedatabase.android.presentation.collections.presenter.CollectionPresenter
import com.themoviedatabase.android.presentation.collections.view.CollectionView
import com.themoviedatabase.android.ui.collections.adapter.CollectionAdapter
import com.themoviedatabase.android.ui.collections.model.MDBItemCollection
import com.themoviedatabase.core.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
abstract class BaseCollectionView : BaseFragment<CollectionPresenter>(), CollectionView , CollectionAdapter.SelectMovieListener{
    @Inject
    lateinit var presenter: CollectionPresenter

    private var binding: FragmentCollectionsBinding? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCollectionsBinding.inflate(inflater, container, false)
        initView()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadCollection()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        presenter.detach()
    }

    override fun initView() {
        binding?.collectionRecyclerview?.layoutManager = LinearLayoutManager(context)
        binding?.collectionErrorNetwork?.btnRetry?.setOnClickListener {
            loadCollection()
            binding?.collectionErrorNetwork?.errorNetworkMain?.visibility = View.GONE
        }
        presenter.attach(this)
    }

    override fun showLoader(show: Boolean) {
        binding?.loader?.loadingView?.visibility = if(show) View.VISIBLE else View.GONE
    }

    override fun showMessage(message: String) {
        if(binding != null) {
            Snackbar.make(binding?.collectionsMainContent!!, message, Snackbar.LENGTH_SHORT).show()
        }

    }

    override fun showMessage(message: Int) {
        if(binding != null) {
            Snackbar.make(binding?.collectionsMainContent!!, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun showCollectionMovies(collection: List<MDBItemCollection>) {
        binding?.collectionRecyclerview?.adapter = CollectionAdapter(collection,this)
    }

    override fun showRetry() {
        binding?.collectionErrorNetwork?.errorNetworkMain?.visibility = View.VISIBLE

    }

    abstract fun loadCollection()


}