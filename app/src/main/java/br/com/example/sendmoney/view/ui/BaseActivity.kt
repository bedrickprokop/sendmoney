package br.com.example.sendmoney.view.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import br.com.example.sendmoney.view.component.ProgressDialog

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    private var mProgressDialog: ProgressDialog = ProgressDialog(this)

    init {
        lifecycle.addObserver(mProgressDialog)
    }

    fun showProgressDialog() {
        mProgressDialog.showProgressDialog(this)
    }

    fun hideProgressDialog() {
        mProgressDialog.hideProgressDialog()
    }
}