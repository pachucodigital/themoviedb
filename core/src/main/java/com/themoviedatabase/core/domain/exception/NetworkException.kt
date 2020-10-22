package com.themoviedatabase.core.domain.exception

import java.net.UnknownHostException

class NetworkException(t: UnknownHostException) : Throwable(t) {
}