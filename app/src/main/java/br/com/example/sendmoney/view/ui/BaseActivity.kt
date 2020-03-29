package br.com.example.sendmoney.view.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.test.espresso.IdlingResource
import br.com.example.sendmoney.util.IdleResourceUtil
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
        IdleResourceUtil.increment()
    }

    fun hideProgressDialog() {
        IdleResourceUtil.decrement()
        mProgressDialog.hideProgressDialog()
    }

    @VisibleForTesting
    fun getCountingIdlingResource(): IdlingResource {
        return IdleResourceUtil.idlingResource
    }
}