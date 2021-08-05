package com.br.diegocunha.mymovies.datasource.resource

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResourceCallAdapter(private val type: Type) : CallAdapter<Type, Call<Resource<Type>>> {
    override fun adapt(call: Call<Type>): Call<Resource<Type>> = ResourceCall(call)
    override fun responseType(): Type = type
}