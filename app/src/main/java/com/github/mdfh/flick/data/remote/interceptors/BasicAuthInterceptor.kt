package com.github.mdfh.flick.data.remote.interceptors

import android.content.Context
import android.util.Log
import com.github.mdfh.flick.data.pref.PrefRepository
import com.github.mdfh.flick.utils.Constants
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.Response

@Singleton
class BasicAuthInterceptor @Inject
constructor(private val context: Context, private val preferencesHelper: PrefRepository) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
       /* val request = chain.request()
        return chain.proceed(request)*/
        Log.d(TAG, "Inside interceptor")
        /*if (!NetworkUtils.isNetworkConnected(context)) {
            Log.d(TAG, "No internet, throwing exception")
            throw NoInternetException("No Internet")
        }*/

        var requestWithApiKey = chain.request().url().newBuilder().addQueryParameter("api_key", Constants.API_KEY).build()

        val authenticatedRequest = chain.request().newBuilder()
            .header("Authorization", "Bearer ${Constants.API_KEY}").build()

        val request = chain.request().newBuilder().url(requestWithApiKey).header("Authorization", "Bearer ${Constants.API_KEY}").build()

        return chain.proceed(request)
    }

    companion object {
        private val TAG = "BasicAuthInterceptor"
    }
}
