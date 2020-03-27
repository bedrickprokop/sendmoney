# SendMoneyApp

Aplicativo Android, desenvolvido em Kotlin, para testes.

## Arquitetura

MVVM - Model, View, ViewModel.

## Componentes do jetpack

- Android KTX
- AppCompat
- Data Binding
- Lifecycles
- LiveData
- ViewModel
- Layout

## Bibliotecas

- CardView
- RecyclerView
- ConstraintLayout
- Retrofit
- OkHttp3

## Acessibilidade

- Possui descrições em componentes visuais que são relevantes e feedbacks(TextToSpeech) para resultados
de ações quando o usuário utilizar-se do Talkback(aplicativo da Google) para navegação no mesmo.

## Observações

- O aplicativo funciona somente na versão mock(buildVariant mockDebug), pois nesta foi criado um
Interceptor(MockClient.kt) que mapeia todos os endpoints e retorna para cada um deles o seu json.

- A versão prod(buildVariant prodDebug) não funciona devido a não existir um servidor backend para
retornar os dados solicitados.

