package com.example.search.ui.view

import com.example.search.R
import com.example.search.base.BaseActivity
import com.example.search.databinding.ActivityMainBinding
import com.example.search.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override val mainViewModel: MainViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }


}