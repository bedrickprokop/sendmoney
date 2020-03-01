package br.com.example.sendmoney.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import br.com.example.sendmoney.R
import br.com.example.sendmoney.databinding.ActHistoryBinding
import br.com.example.sendmoney.viewmodel.HistoryViewModel

class HistoryActivity : AppCompatActivity() {

    private lateinit var bind: ActHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.act_history)
        bind.viewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
    }
}