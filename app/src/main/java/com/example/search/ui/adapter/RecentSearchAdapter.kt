package com.example.search.ui.adapter

import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.search.R
import com.example.search.databinding.ItemRecentSearchBinding
import com.example.search.model.vo.entity.RecentSearch

class RecentSearchAdapter : RecyclerView.Adapter<RecentSearchAdapter.ViewHolder>() {

    private var items = mutableListOf<RecentSearch>()

    private var listener: ((item: RecentSearch) -> Unit)? = null
    private var deleteBtnListener: (( item: RecentSearch) -> Unit)? = null

    fun setOnDeleteClickListener(listener: (item: RecentSearch) -> Unit) {
        this.deleteBtnListener = listener
    }

    fun setOnItemClickListener(listener: (item: RecentSearch) -> Unit) {
        this.listener = listener
    }

    fun setItems(items: List<RecentSearch>){
        this.items = items as MutableList<RecentSearch>
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun remove(item: RecentSearch) {
        items.remove(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_recent_search,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding: ItemRecentSearchBinding? = DataBindingUtil.bind(itemView)

        fun bindView(rs: RecentSearch){
            binding?.recent = rs

            binding?.tvRecentSearchTerms?.setOnClickListener{
                listener?.invoke(rs)
            }

            binding?.tvDelete?.setOnClickListener{
                deleteBtnListener?.invoke(rs)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position])
        d("onBindViewHolder", items[position].word)
    }


}