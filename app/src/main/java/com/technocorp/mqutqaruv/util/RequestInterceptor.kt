package com.technocorp.mqutqaruv.util

import com.technocorp.mqutqaruv.BuildConfig
import com.technocorp.mqutqaruv.data.remote.api.TokenRefreshApi
import com.technocorp.mqutqaruv.data.remote.dto.token.TokenRefreshBody
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RequestInterceptor constructor(
    private val pref: SharedPref,
    private val httpLoggingInterceptor: HttpLoggingInterceptor
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = "Bearer ${pref.accessToken}"
        val newRequest = chain.request().newBuilder()
        if (pref.accessToken?.isNotEmpty()!!) {
            newRequest
                .addHeader("Authorizations", token)
            val response = chain.proceed(newRequest.build())
            return if (response.code == 401) {
                runBlocking {
                    refreshToken(chain)
                }
            } else {
                response
            }
        }
        return chain.proceed(newRequest.build())
    }

    private suspend fun refreshToken(chain: Interceptor.Chain): Response {

        val okHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(120, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                addInterceptor(httpLoggingInterceptor)
            }
        }

        val retrofit = Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            client(okHttpClient.build())
            baseUrl(BuildConfig.API_BASE_URL)
        }.build()

        val api = retrofit.create(TokenRefreshApi::class.java)
        val response = api.tokenRefresh(TokenRefreshBody(pref.refreshToken ?: ""))
        // make an API call to get new token
        return if (response.isSuccessful) {
            val token = response.body()?.access
            pref.refreshToken = response.body()?.refresh
            pref.accessToken = token

            val newRequest = chain.request()
                .newBuilder()
                .header("Authorizations", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        } else {
            chain.proceed(chain.request())
        }
    }
}