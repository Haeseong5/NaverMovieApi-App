package com.example.search.base

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.search.exception.NetworkStateHelper

abstract class BaseFragment<T : ViewDataBinding, R : BaseViewModel> : Fragment(){
    val TAG: String = this.javaClass.simpleName

    lateinit var binding: T

    abstract val layoutResourceId: Int

    abstract val mainViewModel: R

    lateinit var progressDialog: ProgressDialog

    abstract fun initStartView()

    abstract fun initBeforeBinding()

    abstract fun initAfterBinding()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        d(TAG, "onCreateView!!!")
        binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        progressDialog = ProgressDialog(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        d(TAG, "onViewCreated!!!")
        NetworkStateHelper(requireContext(), view, this, duration = 2000)

        initStartView()
        initBeforeBinding()
        initAfterBinding()

    }
}