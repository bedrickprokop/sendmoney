package br.com.example.sendmoney.view.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.example.sendmoney.R
import br.com.example.sendmoney.SendMoneyConsts
import br.com.example.sendmoney.databinding.ActTransferHistoryBinding
import br.com.example.sendmoney.model.entity.Transfer
import br.com.example.sendmoney.model.entity.User
import br.com.example.sendmoney.view.adapter.TransferHistoryAdapter
import br.com.example.sendmoney.view.component.DividerItemDecoration
import br.com.example.sendmoney.viewmodel.TransferHistoryViewModel

class TransferHistoryActivity : BaseActivity() {

    private lateinit var bind: ActTransferHistoryBinding
    private lateinit var adapter: TransferHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.act_transfer_history)
        bind.viewModel = ViewModelProviders.of(this).get(TransferHistoryViewModel::class.java)

        //ActionBar
        setSupportActionBar(bind.tActionBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showProgressDialog()

        val token = intent.getStringExtra(SendMoneyConsts.EXTRA_TOKEN)

        //RecyclerView
        val dividerItemDecoration = DividerItemDecoration(this)
        adapter = TransferHistoryAdapter(this, null)
        bind.rvTransferHistory.adapter = adapter
        bind.rvTransferHistory.addItemDecoration(dividerItemDecoration)
        bind.rvTransferHistory.setHasFixedSize(true)

        val user = getUserSession()
        bind.viewModel?.loadTransferHistoryObservable(user, token)
            ?.observe(this, loadTransferHistoryObservable())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadTransferHistoryObservable(): Observer<List<Transfer>> {
        return Observer {
            adapter.setData(it)
            hideProgressDialog()
        }
    }

    /**
     * Apenas para simular que o usuário foi 'buscado' da 'sessão', ou seja, sharedpreferences, database, etc
     */
    private fun getUserSession(): User {
        val user = User(
            1,
            getString(R.string.act_home_tv_user_name),
            getString(R.string.act_home_tv_user_email),
            null,
            null,
            null
        )
        return user
    }
}