package br.com.example.sendmoney.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel

class TransferViewModel(application: Application) : AndroidViewModel(application) {

    fun isvalidValue(@NonNull value: String): Boolean {
        if (value.isNotEmpty() && value.isNotBlank()) {
            val doubleValue = value.toDouble()
            return doubleValue > 0.0 && doubleValue < 99999999
        }
        return false
    }

}