# MyMoviesApp

MyMoviesApp é um aplicativo que permite ao usuário visualizar uma lista de filmes que serão lançados em breve, assim como detalhes sobre a trama do filme e os gêneros dele.

Ele é uma releitura do [TMDBApp](https://github.com/diegobcunha/TMDBApp) pensando sua construção a tecnologia de [Jetpack Compose](https://developer.android.com/jetpack/compose).
# Tecnologia

### Linguagem
Este aplicativo foi escrito utilizando a linguagem [Kotlin](https://kotlinlang.org/), visto que a linguagem possibilita o uso do paradigma funcional.

### Arquitetura
O aplicativo segue com base da arquitetura [MVVM](https://www.journaldev.com/20292/android-mvvm-design-pattern)(Model-View-ViewModel) e se beneficía das bibliotecas do [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/) e principalmente pelo [Jetpack Compose](https://developer.android.com/jetpack/compose) na construção de view.

O projeto foi dividio em uma camada de datasource em que os dados serão buscados através de provedores de dados com o Retrofit e respeitando assim os princípios do [SOLID](https://en.wikipedia.org/wiki/SOLID).

Dentro da camada de UI, nós buscamos o desenvolvimento voltado a componentes devido da facilidade do Jetpack Compose de reaproveitamento dos mesmos componentes.

### Testes
Os testes irão ser integrados de acordo com a necessidade.

### Bibliotecas externas
Das bibliotecas externas utilizadas no aplicativo, destaca-se o [Jetpack Compose](https://developer.android.com/jetpack/compose) do Jetpack Compose, que visa facilitar construção de telas de forma declarativa e o [Koin](https://insert-koin.io/) para injeção de dependência pois é menos verbosa que o Dagger 2 e 100% escrita em Kotlin assim como a utilização de coroutines para lidar com chamadas assincronas.

### Próximos passos
Os próximos passos do aplicativo se baseam na funcionalidade se salvar a lista de filmes no aplicativo respeitando a ideia de [Offline First](https://www.youtube.com/watch?v=70WqJxymPr8) se utilizando do Room para a camada de persistência de dados.
