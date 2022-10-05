package com.example.project4

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.project4.R.id
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONObject

// --------------------------------//
// CHANGE THIS TO BE YOUR API KEY  //
// --------------------------------//
private const val API_KEY = "https://api.themoviedb.org/3/tv/popular?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US&page=1"


class MovieFragments : Fragment(), OnListFragmentInteractionListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        updateAdapter(progressBar, recyclerView)
        return view
    }


    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()


        val client = AsyncHttpClient()

        client[
                API_KEY,
                object: JsonHttpResponseHandler() {
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        response: String,
                        throwable: Throwable?
                    ) {
                        progressBar.hide()
                        throwable?.message?.let {
                            Log.e("BestSellerBooksFragment", response)
                        }
                    }
                    override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {


                        val gson = Gson()
                        val resultsJSON : String = json.jsonObject.get("results").toString()


                        val arrayBookType = object : TypeToken<List<Movies>>() {}.type
                        val books : List<Movies> = gson.fromJson(resultsJSON, arrayBookType)

                        recyclerView.adapter = MovieRecyclerViewAdapter(books, this@MovieFragments)

                        progressBar.hide()
                        Log.d("BestSellerBooksFragment", resultsJSON)
                    }

                }
        ]



    }


    override fun onItemClick(item: Movies) {
        Toast.makeText(context, "test: " + item.title, Toast.LENGTH_LONG).show()
    }

}