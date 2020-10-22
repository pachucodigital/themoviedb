package com.themoviedatabase.core.domain.exception

import com.themoviedatabase.core.domain.model.MDBApiError

class MDBException(apiError: MDBApiError, code: Int): Throwable()