package com.furkansubasiay.marveltestproject.util

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.furkansubasiay.marveltestproject.R

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */

object Utils
{

    fun showLoadingDialog(context: Context): ProgressDialog {

        var progressDialog: ProgressDialog = ProgressDialog(context, R.style.TransparentProgressDialog)
        if (progressDialog.window != null) {
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        progressDialog.setContentView(R.layout.dialog_progress)
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        return progressDialog
    }
}
