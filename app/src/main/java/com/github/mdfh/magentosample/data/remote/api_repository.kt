/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.github.mdfh.magentosample.data.remote

import android.content.Context
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.Single
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Faraz on 07/07/17.
 */

interface ApiRepository {

}

@Singleton
class AppApiRepository @Inject
constructor(
    private val context : Context,
    private val usersService: UsersService,
    private val gson: Gson
) : ApiRepository
{}