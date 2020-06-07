package com.furkansubasiay.marveltestproject.ui.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.furkansubasiay.marveltestproject.util.Utils
import dagger.android.AndroidInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */

abstract class BaseFragment :
    DaggerFragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    open fun onInject() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(activity)
        super.onCreate(savedInstanceState)

        onInject()
    }

    var progressDialog: ProgressDialog? = null

    fun showLoading(context: Context) {

        if(progressDialog==null)
        {
            progressDialog = Utils.showLoadingDialog(context)
        }

        if(progressDialog!=null && !progressDialog!!.isShowing)
        {
            progressDialog!!.show()
        }
    }


    fun hideLoading() {
        if (progressDialog != null) {
            if (progressDialog!!.isShowing) {
                progressDialog!!.dismiss()
            }
        }
    }


}