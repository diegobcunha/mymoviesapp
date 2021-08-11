package com.br.diegocunha.mymovies.datasource.resource

sealed class Resource<T>(open val data: T?) {

    companion object {

        fun <T> success(data: T? = null) = Success<T>(data)

        fun <T> error(throwable: Throwable? = null, data: T? = null) = Error<T>(throwable)

        fun <T> loading(type: LoadingType? = null, data: T? = null) = Loading<T>(type, data)
    }

    data class Success<T>(override val data: T?) : Resource<T>(data)
    data class Error<T>(val throwable: Throwable?, override val data: T? = null) : Resource<T>(data) {
        fun <R> error() = error<R>(getThrowableOrNull())
    }
    data class Loading<T>(val type: LoadingType?, override val data: T? = null) : Resource<T>(data)

    fun getThrowableOrNull() = (this as? Error)?.throwable

    fun getOrNull() = data

    fun getOrDefault(default: T) = data ?: default

    fun isLoading() = this is Loading

    fun isSuccess() = this is Success

    fun isError() = this is Error

    fun isNotNullSuccess() = this is Success && data != null

    fun loadingType() = (this as? Loading)?.type

    inline fun onLoading(block: (LoadingType?) -> Unit): Resource<T> {
        if (this is Loading) {
            block(type)
        }
        return this
    }

    inline fun onSuccess(block: (T?) -> Unit): Resource<T> {
        if (isSuccess()) {
            block(getOrNull())
        }
        return this
    }

    inline fun onError(block: (Throwable?) -> Unit): Resource<T> {
        if (isError()) {
            block(getThrowableOrNull())
        }
        return this
    }

    inline fun <R> map(mapBlock: (T?) -> R?): Resource<R> {
        return when (this) {
            is Error -> error(throwable)
            is Loading -> loading()
            is Success -> {
                val r = mapBlock(data)
                success(r)
            }
        }
    }

}

/**
 * Indicates the type of loading used in Resource.Loading
 */
enum class LoadingType {
    /**
     * Used when the current content shown on view will be replaced to a new one, usually removing
     * the old one
     */
    REPLACE,

    /**
     * Used when the current content shown on view will be refreshed
     */
    REFRESH,

    /**
     * Used when more content will be shown additionally with the current content
     */
    PAGINATION
}