package com.jammes.boletimnota10.core.repository.api

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(
    private val appID: String,
    private val apiKey: String,
    private val sessionToken: String = ""
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val newRequest = originRequest.newBuilder()
            .addHeader("X-Parse-Application-Id", appID)
            .addHeader("X-Parse-REST-API-Key", apiKey)
            .addHeader("X-Parse-Session-Token", sessionToken)
            .build()

        return chain.proceed(newRequest)
    }
}