package com.example.search.ui.view

import android.content.Context
import android.util.Log.d
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.search.R
import com.example.search.base.BaseFragment
import com.example.search.databinding.FragmentRecentSearchBinding
import com.example.search.ui.adapter.RecentSearchAdapter
import com.example.search.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecentSearchFragment : BaseFragment<FragmentRecentSearchBinding, MainViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_recent_search

    override val mainViewModel: MainViewModel by viewModel()

    private val recentSearchAdapter = RecentSearchAdapter()

    override fun initStartView() {
        recyclerViewSetting()

        recentSearchAdapter.setOnItemClickListener {
            findNavController().navigate(R.id.action_recent_to_search,  bundleOf("word" to it.word))
        }

        recentSearchAdapter.setOnDeleteClickListener {
            mainViewModel.delete(it)
        }
    }

    override fun initBeforeBinding() {
        mainViewModel.getRecentSearch()
    }

    override fun initAfterBinding() {
        observeRecentSearchData()

        mainViewModel.isDeleted.observe(this, Observer {
            recentSearchAdapter.remove(it)
        })
    }

    private fun observeRecentSearchData(){
        mainViewModel.recentSearchData.observe(this, Observer{
            for (i in it.indices){
                d(TAG, it[i].word)
                d(TAG, it[i].id.toString())
            }
            recentSearchAdapter.setItems(it)

        })
    }

    /* recyclerView setting */
    private fun recyclerViewSetting(){
        val layout = { context: Context -> LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        ) }
        binding.mainRvRecentSearch.apply {
            layoutManager = layout(context)
            setHasFixedSize(true)
            adapter = recentSearchAdapter
        }
    }
}