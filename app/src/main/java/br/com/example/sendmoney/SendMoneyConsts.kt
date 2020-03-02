package br.com.example.sendmoney

interface SendMoneyConsts {

    //TODO organizar constantes
    companion object {
        //Config
        val BASE_URL: String = "http://localhost:8080/sendmoney/"

        //Intent
        val EXTRA_TOKEN: String = "TOKEN"

        //Request codes and message results
        val REQUEST_CODE_CONTACTS: Int = 100
        val RESULT_MESSAGE: String = "MESSAGE"
    }
}