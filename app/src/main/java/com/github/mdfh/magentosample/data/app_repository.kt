package com.github.mdfh.magentosample.data

import android.content.Context
import com.github.mdfh.magentosample.data.pref.PrefRepository
import com.github.mdfh.magentosample.data.remote.ApiRepository
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataRepository @Inject
constructor(private val mContext: Context,
            private val mPreferencesHelper: PrefRepository,
            private val mApiHelper: ApiRepository,
            private val mGson: Gson
) : DataRepository

interface DataRepository
{

}