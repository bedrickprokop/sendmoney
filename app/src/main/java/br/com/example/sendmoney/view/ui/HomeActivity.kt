package br.com.example.sendmoney.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.example.sendmoney.R
import br.com.example.sendmoney.databinding.ActHomeBinding
import br.com.example.sendmoney.model.entity.User
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

        val user = getUserSession(intent)

        bind.btSendMoney.setOnClickListener {
            val intent = Intent(
                this, ContactsActivity::class.java
            ).putExtra("token", bind.viewModel?.currentUser?.token)
            startActivity(intent)
        }
        bind.btShowHistory.setOnClickListener {
            val intent = Intent(
                this, HistoryActivity::class.java
            ).putExtra("token", bind.viewModel?.currentUser?.token)
            startActivity(intent)
        }

        bind.viewModel?.currentUser = user
        bind.viewModel?.loadTokenObservable(user)
            ?.observe(this, loadTokenObservable())
    }

    private fun loadTokenObservable(): Observer<String> {
        return Observer {
            it.let {
                bind.viewModel?.currentUser?.token = it
                bind.btShowHistory.isEnabled = true
                bind.btSendMoney.isEnabled = true
            }
        }
    }

    /**
     * Apenas para simular que o usuário foi 'buscado' da 'sessão', ou seja, sharedpreferences, database, etc
     */
    private fun getUserSession(intent: Intent?): User {
        val user = User(
            1,
            getString(R.string.act_home_tv_user_name),
            getString(R.string.act_home_tv_user_email),
            null,
            null
        )
        return user
    }
}