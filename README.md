# SendMoneyApp

Aplicativo Android, desenvolvido em Kotlin, para enviar e visualizar transfências.

## Arquitetura usada

MVVM - Model, View, ViewModel.

## Componentes do jetpack usados

- Android KTX
- AppCompat
- Data Binding
- Lifecycles
- LiveData
- ViewModel
- Layout

## Bibliotecas usadas

- CardView
- RecyclerView
- ConstraintLayout
- Retrofit
- OkHttp3

## Observações:

- O aplicativo funciona somente na versão mock(buildVariant mockDebug), pois nesta foi criado um
Interceptor(MockClient.kt) que mapeia todos os endpoints e retorna para cada deles o seu json.

- A versão prod(buildVariant prodDebug) não funciona devido a não existir um servidor backend para
retornar os dados solicitados.

