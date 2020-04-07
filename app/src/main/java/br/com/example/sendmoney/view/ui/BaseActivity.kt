package br.com.example.sendmoney.view.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.test.espresso.IdlingResource
import br.com.example.sendmoney.R
import br.com.example.sendmoney.util.ColorUtil
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

    //https://learnpainless.com/android/material/make-fully-android-transparent-status-bar
    fun setStatusBarColor(rootView: View, colorResource: Int) {
        var color = ContextCompat.getColor(this, colorResource)
        rootView.fitsSystemWindows = true

        val systemVisibility = if (ColorUtil.isLight(color))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            else {
                //force semiTransparent background color with white icons instead a light
                //background color with gray icons
                color = ContextCompat.getColor(this, R.color.colorSemiTransparent)
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
        else
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        if (Build.VERSION.SDK_INT in Build.VERSION_CODES.KITKAT..Build.VERSION_CODES.KITKAT_WATCH)
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        else
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)

        window.decorView.systemUiVisibility = systemVisibility
        window.statusBarColor = color
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val window = window
        val windowAttributes = window.attributes
        if (on)
            windowAttributes.flags = windowAttributes.flags or bits
        else
            windowAttributes.flags = windowAttributes.flags and bits.inv()
        window.attributes = windowAttributes
    }

    @VisibleForTesting
    fun getCountingIdlingResource(): IdlingResource {
        return IdleResourceUtil.idlingResource
    }
}