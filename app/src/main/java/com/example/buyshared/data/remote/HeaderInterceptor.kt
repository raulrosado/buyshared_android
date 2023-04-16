package com.example.buyshared.data.remote

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor(private val acceessToken: String) :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Accept:", "application/json")
            .addHeader("Authorization:", "Bearer " + acceessToken)
            .build()
        return chain.proceed(request)
    }
}