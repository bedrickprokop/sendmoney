package br.com.example.sendmoney.view.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.example.sendmoney.R
import br.com.example.sendmoney.SendMoneyConsts
import br.com.example.sendmoney.databinding.ActContactsBinding
import br.com.example.sendmoney.model.entity.Contact
import br.com.example.sendmoney.util.SharedUtil
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

        //RecyclerView
        val dividerItemDecoration = DividerItemDecoration(this)
        adapter = ContactsAdapter(this, null) { contact ->

            val transferDialog = TransferDialog(this, contact) { value ->
                showProgressDialog()

                val token = SharedUtil.getUser(this).token
                bind.viewModel?.sendMoney(contact, value, token)
                    ?.observe(this, sendMoneyObservable())
            }
            transferDialog.show()
        }
        bind.rvContacts.adapter = adapter
        bind.rvContacts.addItemDecoration(dividerItemDecoration)
        bind.rvContacts.setHasFixedSize(true)

        val user = SharedUtil.getUser(this)
        bind.viewModel?.loadContactListObservable(user)
            ?.observe(this, loadContactListObservable())
    }

    private fun sendMoneyObservable(): Observer<in Boolean> {
        return Observer {
            val intent = Intent()
            intent.putExtra(
                SendMoneyConsts.RESULT_MESSAGE,
                getString(R.string.result_message_send_money_success)
            )
            setResult(Activity.RESULT_OK, intent)

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
}