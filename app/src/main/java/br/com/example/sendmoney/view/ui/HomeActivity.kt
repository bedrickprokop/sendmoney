package br.com.example.sendmoney.view.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.example.sendmoney.*
import br.com.example.sendmoney.databinding.ActHomeBinding
import br.com.example.sendmoney.model.entity.User
import br.com.example.sendmoney.util.SharedUtil
import br.com.example.sendmoney.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar

class HomeActivity : BaseActivity() {

    private lateinit var bind: ActHomeBinding
    private lateinit var user: User
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        bind = DataBindingUtil.setContentView(this, R.layout.act_home)
        bind.viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        showProgressDialog()

        bind.btSendMoney.setOnClickListener {
            startActivityForResult(
                Intent(this, ContactsActivity::class.java),
                REQUEST_CODE_CONTACTS
            )
        }
        bind.btShowHistory.setOnClickListener {
            startActivity(Intent(this, TransferHistoryActivity::class.java))
        }

        textToSpeech = TextToSpeech(this, TextToSpeech.OnInitListener {
            if (it == TextToSpeech.SUCCESS)
                textToSpeech.language = ENUS
        })

        user = SharedUtil.getUser(this)
        bind.viewModel?.currentUser = user
        bind.viewModel?.loadTokenObservable(user)?.observe(this, loadTokenObservable())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_CONTACTS) {
                val resultMessage = data?.getCharSequenceExtra(RESULT_MESSAGE)
                Handler().postDelayed({
                    Snackbar.make(bind.clContainer, resultMessage!!, Snackbar.LENGTH_LONG).show()
                    textToSpeech.speak(resultMessage, TextToSpeech.QUEUE_ADD, null, "1")
                }, 500)
            }
        }
    }

    private fun loadTokenObservable(): Observer<String> {
        return Observer {
            it.let {
                //Adiciona o token ao usuário que está no sharedpreferences e no viewModel
                SharedUtil.addString(this, KEY_USER_TOKEN, it)
                bind.viewModel?.currentUser?.token = it

                bind.tvUserName.text = bind.viewModel?.currentUser?.name
                bind.tvUserEmail.text = bind.viewModel?.currentUser?.email
                bind.btShowHistory.isEnabled = true
                bind.btSendMoney.isEnabled = true
            }
            hideProgressDialog()
        }
    }

    override fun onPause() {
        textToSpeech.stop()
        textToSpeech.shutdown()
        super.onPause()
    }
}