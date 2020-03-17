package br.com.example.sendmoney.model.entity

data class User(
    var id: Int?,
    var name: String = "",
    var email: String = "",
    var token: String = "",
    var contactList: List<Contact>?,
    var transferList: List<Transfer>?
)