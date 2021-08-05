package com.br.diegocunha.mymovies.datasource.api.rest

import com.br.diegocunha.mymovies.BuildConfig
import com.br.diegocunha.mymovies.datasource.resource.ResourceCallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val restModule = module {

    single { retrofit(get(), get()).create(TmdbApi::class.java) }

    factory { TmdbExecutor(get()) }
}

private fun Scope.retrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(ResourceCallAdapterFactory())
        .build()
}