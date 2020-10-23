package com.themoviedatabase.core.domain.exception

import com.themoviedatabase.core.domain.model.MDBApiError

class MDBException(val apiError: MDBApiError, val code: Int): Throwable()