package com.br.diegocunha.mymovies.coroutines

import org.koin.dsl.module


val coroutineModule = module {
    factory <DispatchersProvider> { ProductionDispatcherProvider }
}