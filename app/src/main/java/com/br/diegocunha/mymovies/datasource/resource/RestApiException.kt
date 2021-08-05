package com.br.diegocunha.mymovies.datasource.resource

import androidx.annotation.Keep
import com.br.diegocunha.mymovies.datasource.exception.StringResourceException
import com.br.diegocunha.mymovies.datasource.json.fromJson
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.util.regex.Pattern

@Keep
class RestApiException(
    private val httpCode: Int,
    val errorResponse: ErrorResponse? = null,
    cause: Throwable?,
) : StringResourceException(errorResponse?.message, cause = cause) {

    constructor(httpException: HttpException) : this(
        httpException.code(),
        httpException.response()?.errorBody()?.serializeErrorBody(),
        httpException
    )
}

@Keep
data class ErrorResponse(
    val message: String? = null,
    val custom: JSONObject? = null,
) {

    fun getFieldError(): String? {
        val msg = message
        return if (msg != null) {
            val pattern =
                Pattern.compile("(field_)([\\w\\d]*)(_error_)")
            val matcher = pattern.matcher(msg)
            if (matcher.find() && matcher.groupCount() == 3) {
                matcher.group(2)
            } else {
                null
            }
        } else {
            null
        }
    }
}

fun ResponseBody.serializeErrorBody(): ErrorResponse? {
    return try {
        charStream().fromJson<ErrorResponse>()
    } catch (e: JSONException) {
        ErrorResponse()
    } catch (e: IOException) {
        ErrorResponse()
    }
}