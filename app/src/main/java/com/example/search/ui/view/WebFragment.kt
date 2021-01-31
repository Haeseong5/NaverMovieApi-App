package com.example.search.ui.view

import android.view.KeyEvent
import androidx.navigation.fragment.findNavController
import com.example.search.R
import com.example.search.base.BaseFragment
import com.example.search.databinding.FragmentWebviewBinding
import com.example.search.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class WebFragment : BaseFragment<FragmentWebviewBinding, MainViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_webview

    override val mainViewModel: MainViewModel by viewModel()


    override fun initStartView() {
        arguments?.getString("link")?.run {
            binding.webView.loadUrl(this);
        }

//        binding.webView.setOnKeyListener { _, keyCode, event ->
//                    //This is the filter
//            findNavController().navigate(R.id.action_web_to_search)
//
//            if (event.action != KeyEvent.ACTION_DOWN)
//                return@setOnKeyListener true
//
//
//            if (keyCode == KeyEvent.KEYCODE_BACK) {
//                if (binding.webView.canGoBack()) {
//                    binding.webView.goBack()
//                } else {
//                    (activity as MainActivity).onBackPressed()
//                }
//
//                return@setOnKeyListener true;
//            }
//
//            return@setOnKeyListener false;
//
//        }
    }

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }


}