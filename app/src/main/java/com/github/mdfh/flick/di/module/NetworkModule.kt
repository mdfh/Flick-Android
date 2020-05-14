package com.github.mdfh.flick.di.module

import android.content.Context
import com.github.mdfh.flick.data.remote.interceptors.AuthenticatorInterceptor
import com.github.mdfh.flick.BuildConfig
import com.github.mdfh.flick.data.remote.UsersService
import com.github.mdfh.flick.data.remote.interceptors.BasicAuthInterceptor
import com.google.gson.Gson
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Module which provides all required dependencies about network
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    internal fun provideUsersInterface(retrofit: Retrofit): UsersService {
        return retrofit.create(UsersService::class.java)
    }

    @Provides
    @Singleton
    internal fun getRequestHeader(
        context: Context,
        basicAuthInterceptor: BasicAuthInterceptor,
        authenticatorInterceptor: AuthenticatorInterceptor
    ): OkHttpClient {

        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder().build()
                chain.proceed(request)
            }
            .addInterceptor(basicAuthInterceptor)
            .connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)

        httpClient.authenticator(authenticatorInterceptor)
        return httpClient.build()
    }
}
