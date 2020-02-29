package br.com.example.sendmoney.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import br.com.example.sendmoney.R
import br.com.example.sendmoney.databinding.ActContactsBinding
import br.com.example.sendmoney.viewmodel.ContactsViewModel

class ContactsActivity : AppCompatActivity() {

    private lateinit var bind: ActContactsBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.act_contacts)
        bind.viewModel = ViewModelProviders.of(this).get(ContactsViewModel::class.java)
    }
}