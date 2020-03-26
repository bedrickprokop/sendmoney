package br.com.example.sendmoney.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel

class TransferViewModel(application: Application) : AndroidViewModel(application) {

    fun isValidValue(@NonNull value: Double): Boolean {
        return value > 0.0
    }

}