package com.github.mdfh.flick.data.remote.interceptors

import android.content.Context
import com.github.mdfh.flick.data.pref.PrefRepository
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Faraz on 2/21/2017.
 */
@Singleton
class BasicAuthInterceptor @Inject
constructor(private val context: Context, private val preferencesHelper: PrefRepository) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(request)
        /*Log.d(TAG, "Inside interceptor")
        if (!NetworkUtils.isNetworkConnected(context)) {
            Log.d(TAG, "No internet, throwing exception")
            throw NoInternetException("No Internet")
        }
        val request = chain.request()

        if(preferencesHelper.getToken() != null) {
            var credentials = preferencesHelper.getToken();
            val authenticatedRequest = request.newBuilder()
                    .header("Authorization", "Bearer $credentials").build()
            return chain.proceed(authenticatedRequest)
        }
        return chain.proceed(request)*/
    }

    companion object {
        private val TAG = "BasicAuthInterceptor"
    }
}
