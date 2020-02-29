package br.com.example.sendmoney.view

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import br.com.example.sendmoney.R
import br.com.example.sendmoney.databinding.ActHomeBinding
import br.com.example.sendmoney.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var bind: ActHomeBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        bind = DataBindingUtil.setContentView(this, R.layout.act_home)
        bind.viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }
}