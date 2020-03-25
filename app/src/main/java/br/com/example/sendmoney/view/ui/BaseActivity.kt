package br.com.example.sendmoney.view.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.example.sendmoney.view.component.ProgressDialog

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    private lateinit var mProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mProgressDialog = ProgressDialog(this)
        lifecycle.addObserver(mProgressDialog)
    }

    fun showProgressDialog() {
        mProgressDialog.showProgressDialog()
    }

    fun hideProgressDialog() {
        mProgressDialog.hideProgressDialog()
    }
}