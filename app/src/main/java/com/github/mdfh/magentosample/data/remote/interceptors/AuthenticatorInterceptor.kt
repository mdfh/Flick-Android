package com.github.mdfh.magentosample.data.remote.interceptors

import android.content.Context
import android.util.Log
import com.github.mdfh.magentosample.BuildConfig
import com.github.mdfh.magentosample.data.pref.PrefRepository
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

@Singleton
class AuthenticatorInterceptor @Inject constructor(private val context: Context,
                                                   private val gson : Gson,
                                                   private val prefRepository: PrefRepository) : Authenticator {

    @Throws(IOException::class)
    override fun authenticate(route: Route, response: Response): Request? {
        Log.d(TAG, "authenticate()")

        /*if (responseCount(response) >= 3) {
            return null // If we've failed 3 times, give up.
        }*/

        return null;

        /*if(preferencesHelper.getRegistrationPrefCall() == null)
            return null

        val credentials = preferencesHelper.getRegistrationPrefCall()!!.token
        if (credentials == response.request().header("Authorization")) {
            return null // If we already failed with these credentials, don't retry.
        }

        var tokenResponse = getTokenRequestObservable();

        if(tokenResponse != null) {
            preferencesHelper.setToken(tokenResponse.token)
            return *//*if (responseCount(response) >= 3) {
            null // If we've failed 3 times, give up.
        } else *//* response.request().newBuilder()
                    .header("Authorization", "Bearer ${tokenResponse.token}")
                    .build()
        }
        else
            return null;*/
    }

    /*private fun getTokenRequestObservable() : TokenResponse?
    {
        val userType = UserType.fromValue(preferencesHelper.getRegistrationPrefCall()!!.type)
        var tokenCall : Call<TokenResponse>? = null
        if(userType == UserType.USER)
        {
            val deviceId = preferencesHelper.getDeviceId();
            if(deviceId != null) {
                val request = GuestUserTokenRequest(deviceId)
                tokenCall =  getRetrofitInstance().create(TokenService::class.java).token(request)
            }
        }
        else
        {
            val email = preferencesHelper.getRegisteredEmail()
            val password = preferencesHelper.getPassword()
            if(email != null && password != null)
            {
                val request = RegisteredUserTokenRequest(email, password)
                tokenCall =  getRetrofitInstance().create(TokenService::class.java).token(request)
            }
        }

        if(tokenCall != null)
        {
            var response = tokenCall.execute()
            if(response != null && response.isSuccessful)
                return response.body()
        }

        return null
    }*/

    fun getRetrofitInstance() : Retrofit{
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(getOkHttp())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    private fun getOkHttp() : OkHttpClient
    {
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder().build()
            chain.proceed(request)
        }
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)

        // httpClient.authenticator(authenticatorInterceptor)
//        httpClient.addInterceptor(ChuckInterceptor(context))
        return httpClient.build()
    }

    private fun responseCount(response: Response?): Int {
        var response = response
        var result = 1

        while ({ response = response!!.priorResponse() }() != null) {
            result++
        }
        return result
    }

    companion object {
        private val TAG = "AuthInterceptor"
    }
}