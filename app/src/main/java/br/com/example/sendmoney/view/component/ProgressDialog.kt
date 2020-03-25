package br.com.example.sendmoney.view.component

import android.app.Activity
import android.app.AlertDialog
import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import br.com.example.sendmoney.R

class ProgressDialog(context: Activity) : LifecycleObserver {

    private var mAlertDialog: AlertDialog = newInstance(context)

    companion object {
        fun newInstance(context: Activity): AlertDialog {
            val builder = AlertDialog.Builder(context)
            val view = context.layoutInflater.inflate(R.layout.dialog_progress, null)
            builder.setView(view)

            val alertDialog = builder.create()
            alertDialog.setCancelable(false)

            alertDialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
            return alertDialog
        }
    }

    fun showProgressDialog() {
        mAlertDialog.show()
    }

    fun hideProgressDialog() {
        if (mAlertDialog.isShowing)
            mAlertDialog.dismiss()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        hideProgressDialog()
    }
}