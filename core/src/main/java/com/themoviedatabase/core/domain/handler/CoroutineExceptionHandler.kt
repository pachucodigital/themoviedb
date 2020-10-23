package com.themoviedatabase.core.domain.handler

import android.util.Log
import com.themoviedatabase.core.domain.exception.MDBException
import com.themoviedatabase.core.domain.exception.NetworkException
import com.themoviedatabase.core.domain.exception.ServerException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import java.net.UnknownHostException

class CoroutineExceptionHandler: Function <Throwable> {

    fun apply(t: Throwable): Throwable {
        return when (t) {
            // Returns an Observable with a HttpException if the error is from Http.
            is HttpException -> {

               val response = t.response()
               val errorBody =  t.response()?.errorBody()?.string()
               if(response != null && errorBody != null) {
                   getResponseState(errorBody, response.code())
               } else {
                   t
               }
            }

            is UnknownHostException -> {
                NetworkException(t)
            }

            else -> {
                t
            }
        }

    }

    private fun getResponseState(errorBody: String, code: Int): Throwable {
        return if(code == 404 || code == 401 || code == 400) { // validate if is a Error from API
            MDBException(Json.decodeFromString(errorBody), code)
        } else if(code >= 500) { // Server Error
            ServerException(code)
        } else { //
            Exception("The specific HTTP request has been interrupted $code")
        }

    }

}