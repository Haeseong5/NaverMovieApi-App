package com.example.search.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.search.exception.NetworkStateHelper

abstract class BaseActivity<T : ViewDataBinding, R : BaseViewModel> : AppCompatActivity(){
    val TAG: String = this.javaClass.simpleName

    lateinit var binding: T

    abstract val layoutResourceId: Int

    abstract val mainViewModel: R

    lateinit var progressDialog: ProgressDialog

    abstract fun initStartView()

    abstract fun initBeforeBinding()

    abstract fun initAfterBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutResourceId)

        progressDialog = ProgressDialog(this)

        NetworkStateHelper(applicationContext, findViewById(android.R.id.content), this)

        initStartView()
        initBeforeBinding()
        initAfterBinding()
    }
}