package com.example.search.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.search.R
import com.example.search.databinding.ItemSearchMovieBinding
import com.example.search.model.vo.dto.NaverMovieResponse

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var items = mutableListOf<NaverMovieResponse.Item>()

    private var listener: ((item: NaverMovieResponse.Item) -> Unit)? = null

    fun setOnItemClickListener(listener: (item: NaverMovieResponse.Item) -> Unit) {
        this.listener = listener
    }

    fun setItems(items: List<NaverMovieResponse.Item>){
        this.items = items as MutableList<NaverMovieResponse.Item>
        notifyDataSetChanged()
    }

    fun resetAll(old: List<NaverMovieResponse.Item>) {
        items.clear()
        items.addAll(old)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_search_movie,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding: ItemSearchMovieBinding? = DataBindingUtil.bind(itemView)

        fun bindView(movie: NaverMovieResponse.Item){
            binding?.movie = movie

            binding?.root?.setOnClickListener{
                listener?.invoke(movie)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position])
    }

}