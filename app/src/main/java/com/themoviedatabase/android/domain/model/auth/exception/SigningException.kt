package com.themoviedatabase.android.domain.model.auth.exception

class SigningException(val code: Int) : Throwable() {

    companion object {
        const val ALL_FIELDS_EMPTY = 1
        const val USER_IS_EMPTY = 2
        const val PASSWORD_IS_EMPTY = 3
        const val PASSWORD_TO_SHORT = 4
    }
}