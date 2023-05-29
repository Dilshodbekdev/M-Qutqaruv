package com.technocorp.mqutqaruv.util

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor constructor(private val pref: SharedPref) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = "Bearer ${pref.token}"
        val newRequest = chain.request().newBuilder()
        if (pref.token?.isNotEmpty()!!) {
            newRequest
                .addHeader("Authorizations", token)
        }
        return chain.proceed(newRequest.build())
    }
}
