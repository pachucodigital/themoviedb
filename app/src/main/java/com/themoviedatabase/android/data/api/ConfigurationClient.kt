package com.themoviedatabase.android.data.api

import okhttp3.Interceptor
import java.util.concurrent.TimeUnit

class ConfigurationClient(val readTimeOut: Long, val connectTimeOut: Long, val timeUnit: TimeUnit, val listInterceptor: List<Interceptor>)