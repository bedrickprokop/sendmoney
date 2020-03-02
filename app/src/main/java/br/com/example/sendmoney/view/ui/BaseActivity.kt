package br.com.example.sendmoney.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.example.sendmoney.view.component.ProgressDialog

open class BaseActivity : AppCompatActivity() {

    private var mProgressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog?.showProgressDialog(this)
        } else {
            mProgressDialog = ProgressDialog(this)
            lifecycle.addObserver(mProgressDialog!!)
            mProgressDialog?.showProgressDialog(this)
        }
    }

    fun hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog?.hideProgressDialog()
        }
    }
}