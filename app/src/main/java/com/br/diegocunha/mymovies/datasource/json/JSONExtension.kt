package com.br.diegocunha.mymovies.datasource.json

import com.squareup.moshi.Moshi
import java.io.Reader

inline fun <reified T> Reader.fromJson(moshi: Moshi = MoshiManager.moshi): T? {
    val adapter = moshi.adapter(T::class.java)
    return adapter.fromJson(this.readText())
}