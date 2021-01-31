package com.example.search.ui.view

import android.content.Context
import android.util.Log.d
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.search.R
import com.example.search.base.BaseFragment
import com.example.search.databinding.FragmentSearchBinding
import com.example.search.model.vo.entity.RecentSearch
import com.example.search.ui.adapter.SearchAdapter
import com.example.search.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding, MainViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_search

    override val mainViewModel: MainViewModel by viewModel()

    private val searchAdapter = SearchAdapter()

    override fun initStartView() {
        recyclerViewSetting()
        searchViewSetting()

        //최근 검색어 클릭 시 프래그먼트 이동
        binding.mainTvRecentSearch.setOnClickListener {
            findNavController().navigate(R.id.action_search_to_recent)
        }

        //선택한 최근 검색어로 영화 검색
        arguments?.getString("word")?.run {
                mainViewModel.getMovies(this)
                binding.mainSearchView.onActionViewExpanded() //searchView 활성화
                binding.mainSearchView.setQuery(this, false) //searchView 에 검색어 설정
        }

        //리사이클러뷰 아이템 클릭리스너
        searchAdapter.setOnItemClickListener {
            findNavController().navigate(R.id.action_search_to_web,  bundleOf("link" to it.link))
        }


    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {
        observeSearchMovieData()

        mainViewModel.isInserted.observe(this, Observer {
            d(TAG, "inserted $it")
        })
    }

    private fun observeSearchMovieData(){
        mainViewModel.movieResponseData.observe(this, Observer{
            searchAdapter.setItems(it.items)
            searchAdapter.notifyDataSetChanged()
        })
    }

    /* recyclerView setting */
    private fun recyclerViewSetting(){
        binding.mainRvSearchMovie.apply {
            layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
            )
            setHasFixedSize(true)
            adapter = searchAdapter
        }

    }

    /* searchView setting */
    private fun searchViewSetting(){
        binding.mainSearchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mainViewModel.getMovies(query)
                mainViewModel.saveSearchWord(RecentSearch(word = query))
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
//                mainViewModel.getMovies(query)
                return true
            }

        })

        binding.mainSearchView.setOnCloseListener {
            searchAdapter.clear()
            return@setOnCloseListener true
        }
    }

}
