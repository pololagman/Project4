package com.example.project4

import android.content.Intent
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project4.R.id


class MovieRecyclerViewAdapter(
    private val books: List<Movies>,
    private val mListener: OnListFragmentInteractionListener?
)
    : RecyclerView.Adapter<MovieRecyclerViewAdapter.BookViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movies, parent, false)
        return BookViewHolder(view)
    }


    inner class BookViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Movies? = null
        val mBookTitle: TextView = mView.findViewById<View>(id.book_title) as TextView
        val mBookDescription: TextView = mView.findViewById<View>(id.book_description) as TextView
        val mMovieImage: ImageView = mView.findViewById(id.movieImage) as ImageView

        override fun toString(): String {
            return mBookTitle.toString()
        }
    }


    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]

        holder.mItem = book
        holder.mBookTitle.text = book.title
        holder.mBookDescription.text = book.description
        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500/" + book.poster)
            .centerInside()
            .into(holder.mMovieImage)

        holder.mView.setOnClickListener {
            holder.mItem?.let { book ->
                mListener?.onItemClick(book)
            }
        }
    }



    override fun getItemCount(): Int {
        return books.size
    }
}