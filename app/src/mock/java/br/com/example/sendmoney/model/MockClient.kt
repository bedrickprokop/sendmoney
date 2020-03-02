package br.com.example.sendmoney.model

import okhttp3.*

class MockClient : Interceptor {

    val POST_TOKEN_GENERATE_ENDPOINT = "/sendmoney/token/generate"
    val POST_TOKEN_GENERATE_RESPONSE: String = """"CD344689F90""""

    val GET_CONTACTS_ENDPOINT = "/sendmoney/user/contacts//CD344689F90"
    val GET_CONTACTS_RESPONSE =
        """[{"id":1,"name":"Gollum","phone":"(31) 90383-4001"},{"id":2,"name":"Frodo Baggins","phone":"(31) 99801-2942"},{"id":3,"name":"Bilbo Baggins","phone":"(11) 90383-4001"},{"id":4,"name":"Gandalf","phone":"(12) 90383-4001"},{"id":5,"name":"Éowyn","phone":"(11) 90383-4001"},{"id":6,"name":"Arwen Undómiel","phone":"(31) 90383-4001"},{"id":7,"name":"Barbárvore","phone":"(98) 90383-4001"},{"id":8,"name":"Velho Salgueiro-homem","phone":"(97) 90383-4001"},{"id":9,"name":"Fredegar Bolger","phone":"(22) 90383-4001"},{"id":10,"name":"Forlong, o Gordo","phone":"(11) 90383-4001"},{"id":11,"name":"Radagast","phone":"(12) 90383-4001"},{"id":12,"name":"Faramir","phone":"(97) 90383-4001"},{"id":13,"name":"Rei Bruxo de Angmar","phone":"(21) 90383-4001"},{"id":14,"name":"Thórin II Escudo de Carvalho","phone":"(11) 90383-4001"},{"id":15,"name":"Grão Orc","phone":"(31) 90383-4001"}]"""

    val GET_TRANSFERS_ENDPOINT = "/sendmoney/transfer/CD344689F90"
    val GET_TRANSFERS_RESPONSE =
        """[{"id":1,"name":"Gollum","phone":"(31) 90383-4001","value":12.23},{"id":2,"name":"Frodo Baggins","phone":"(31) 99801-2942","value":544.11},{"id":3,"name":"Bilbo Baggins","phone":"(11) 90383-4001","value":0.01},{"id":4,"name":"Gandalf","phone":"(12) 90383-4001","value":60500},{"id":5,"name":"Éowyn","phone":"(11) 90383-4001","value":301.23},{"id":6,"name":"Arwen Undómiel","phone":"(31) 90383-4001","value":99.01},{"id":7,"name":"Barbárvore","phone":"(98) 90383-4001","value":302.5},{"id":8,"name":"Velho Salgueiro-homem","phone":"(97) 90383-4001","value":77.99},{"id":9,"name":"Fredegar Bolger","phone":"(22) 90383-4001","value":109},{"id":10,"name":"Forlong, o Gordo","phone":"(11) 90383-4001","value":800},{"id":11,"name":"Radagast","phone":"(12) 90383-4001","value":51900.32},{"id":12,"name":"Faramir","phone":"(97) 90383-4001","value":0.78},{"id":13,"name":"Rei Bruxo de Angmar","phone":"(21) 90383-4001","value":1.3},{"id":14,"name":"Thórin II Escudo de Carvalho","phone":"(11) 90383-4001","value":435.34},{"id":15,"name":"Grão Orc","phone":"(31) 90383-4001","value":12.23}]"""

    val POST_TRANSFER_SEND_MONEY_ENDPOINT = "/sendmoney/transfer/sendmoney"
    val POST_TRANSFER_SEND_MONEY_RESPONSE = """"true""""

    val EMPTY_STRING = ""
    val CONTENT_TYPE_KEY = "content-type"
    val CONTENT_TYPE_VALUE = "application/json"

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url()
        val response = when (url.encodedPath()) {
            POST_TOKEN_GENERATE_ENDPOINT -> POST_TOKEN_GENERATE_RESPONSE
            GET_CONTACTS_ENDPOINT -> GET_CONTACTS_RESPONSE
            GET_TRANSFERS_ENDPOINT -> GET_TRANSFERS_RESPONSE
            POST_TRANSFER_SEND_MONEY_ENDPOINT -> POST_TRANSFER_SEND_MONEY_RESPONSE
            else -> EMPTY_STRING
        }

        return Response.Builder()
            .code(200)
            .message(response)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_1)
            .body(
                ResponseBody.create(
                    MediaType.parse(CONTENT_TYPE_VALUE),
                    response.toByteArray()
                )
            )
            .addHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE)
            .build()
    }
}