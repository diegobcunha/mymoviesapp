package com.br.diegocunha.mymovies.datasource.resource

import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Delegate class responsible to convert Retrofit response to resource
 */
abstract class CallDelegate<I, O>(protected val proxy: Call<I>) : Call<O> {
    override fun enqueue(callback: Callback<O>) = enqueueImpl(callback)
    override fun clone(): Call<O> = cloneImpl()
    override fun isExecuted(): Boolean = proxy.isExecuted
    override fun isCanceled(): Boolean = proxy.isCanceled
    override fun cancel() = proxy.cancel()
    override fun request(): Request = proxy.request()

    override fun execute(): Response<O> {
        throw NotImplementedError()
    }

    protected abstract fun enqueueImpl(callback: Callback<O>)
    protected abstract fun cloneImpl(): Call<O>
}