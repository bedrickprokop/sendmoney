package br.com.example.sendmoney.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.example.sendmoney.R
import br.com.example.sendmoney.SendMoneyConsts
import br.com.example.sendmoney.databinding.ActHomeBinding
import br.com.example.sendmoney.model.entity.User
import br.com.example.sendmoney.viewmodel.HomeViewModel

class HomeActivity : BaseActivity() {

    private lateinit var bind: ActHomeBinding;
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        bind = DataBindingUtil.setContentView(this, R.layout.act_home)
        bind.viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        showProgressDialog()

        bind.btSendMoney.setOnClickListener {
            val intent = Intent(
                this, ContactsActivity::class.java
            ).putExtra(SendMoneyConsts.EXTRA_TOKEN, bind.viewModel?.currentUser?.token)
            startActivity(intent)
        }
        bind.btShowHistory.setOnClickListener {
            val intent = Intent(
                this, TransferHistoryActivity::class.java
            ).putExtra(SendMoneyConsts.EXTRA_TOKEN, bind.viewModel?.currentUser?.token)
            startActivity(intent)
        }

        user = getUserSession(intent)
        bind.viewModel?.currentUser = user
        bind.viewModel?.loadTokenObservable(user)
            ?.observe(this, loadTokenObservable())
    }

    private fun loadTokenObservable(): Observer<String> {
        return Observer {
            it.let {
                bind.viewModel?.currentUser?.token = it
                bind.tvUserName.text = bind.viewModel?.currentUser?.name
                bind.tvUserEmail.text = bind.viewModel?.currentUser?.email
                bind.btShowHistory.isEnabled = true
                bind.btSendMoney.isEnabled = true
            }
            hideProgressDialog()
        }
    }

    /**
     * Apenas para simular que o usuário foi 'buscado' da 'sessão', ou seja, sharedpreferences, database, etc
     */
    private fun getUserSession(intent: Intent?): User {
        val user = User(
            1,
            "Bedrick Prokop",
            "bedrick.prokop@gmail.com",
            null,
            null,
            null
        )
        return user
    }
}