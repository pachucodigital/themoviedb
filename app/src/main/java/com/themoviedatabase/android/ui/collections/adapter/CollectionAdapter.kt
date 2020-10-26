package com.themoviedatabase.android.ui.collections.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.themoviedatabase.android.R
import com.themoviedatabase.android.ui.collections.model.MDBItemCollection

class CollectionAdapter(private val results: List<MDBItemCollection>, private val onItemSelectedListener: SelectMovieListener?) : RecyclerView.Adapter<CollectionAdapter.MoviesCollectionHolder>() {

    class MoviesCollectionHolder(itemView: View, private val onItemSelectedListener: SelectMovieListener?) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: MDBItemCollection) {

            itemView.setOnClickListener {
                onItemSelectedListener?.onSelectMovie(item.id)
            }
            itemView.findViewById<TextView>(R.id.item_movie_title).text = item.title
            itemView.findViewById<TextView>(R.id.item_movie_subtile).text = item.overview
            itemView.findViewById<TextView>(R.id.item_movie_extras).text = item.extras
            itemView.findViewById<SimpleDraweeView>(R.id.item_movie_poster).setImageURI("https://image.tmdb.org/t/p/w200${item.poster_path}")
        }

        companion object {
            fun create(parent: ViewGroup, onItemSelectedListener: SelectMovieListener?): MoviesCollectionHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
                return MoviesCollectionHolder(view, onItemSelectedListener)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesCollectionHolder {
        return MoviesCollectionHolder.create(parent, onItemSelectedListener)
    }

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: MoviesCollectionHolder, position: Int) {
        holder.bind(results[position])
    }

    interface SelectMovieListener {
        fun onSelectMovie(movieId: Int)
    }


}