package com.br.diegocunha.mymovies.extensions

import android.content.Intent
import com.br.diegocunha.mymovies.extensions.exceptions.InvalidComponentInitializationException


fun Intent.getIntOrThrow(key: String): Int {
    return if (hasExtra(key)) {
        getIntExtra(key, 0)
    } else {
        throw InvalidComponentInitializationException(
            key
        )
    }
}

inline fun <reified T : Enum<T>> Intent.getEnumExtra(key: String): T? {
    return try {
        val enumId = getIntExtra(key, -1)
        if (enumId == -1) null else T::class.java.enumConstants?.get(enumId)
    } catch (e: IndexOutOfBoundsException) {
        null
    }
}