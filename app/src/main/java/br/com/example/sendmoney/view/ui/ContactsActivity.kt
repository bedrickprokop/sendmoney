package br.com.example.sendmoney.view.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.example.sendmoney.R
import br.com.example.sendmoney.SendMoneyConsts
import br.com.example.sendmoney.databinding.ActContactsBinding
import br.com.example.sendmoney.model.entity.Contact
import br.com.example.sendmoney.model.entity.User
import br.com.example.sendmoney.view.adapter.ContactsAdapter
import br.com.example.sendmoney.view.component.DividerItemDecoration
import br.com.example.sendmoney.viewmodel.ContactsViewModel

class ContactsActivity : BaseActivity() {

    private lateinit var bind: ActContactsBinding
    private lateinit var adapter: ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.act_contacts)
        bind.viewModel = ViewModelProviders.of(this).get(ContactsViewModel::class.java)

        //ActionBar
        setSupportActionBar(bind.tActionBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showProgressDialog()

        val token = intent.getStringExtra(SendMoneyConsts.EXTRA_TOKEN)

        //RecyclerView
        val dividerItemDecoration = DividerItemDecoration(this)
        adapter = ContactsAdapter(this, null) { contact ->

            val transferDialog = TransferDialog(this, contact) { value ->
                showProgressDialog()
                bind.viewModel?.sendMoney(contact, value, token)
                    ?.observe(this, sendMoneyObservable())
            }
            transferDialog.show()
        }
        bind.rvContacts.adapter = adapter
        bind.rvContacts.addItemDecoration(dividerItemDecoration)
        bind.rvContacts.setHasFixedSize(true)

        val user = getUserSession()
        bind.viewModel?.loadContactListObservable(user, token)
            ?.observe(this, loadContactListObservable())
    }

    private fun sendMoneyObservable(): Observer<in Boolean> {
        return Observer {
            //TODO Exibir feedback sucesso ou falha
            //TODO Voltar para a tela home
            hideProgressDialog()
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadContactListObservable(): Observer<List<Contact>> {
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