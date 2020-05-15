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

package com.github.mdfh.flick.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.github.mdfh.flick.di.ApiInfo
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by amitshekhar on 07/07/17.
 */

@Singleton
class ApiHeader @Inject
constructor(val publicApiHeader: PublicApiHeader, val protectedApiHeader: ProtectedApiHeader) {

    class ProtectedApiHeader(@field:Expose
                             @field:SerializedName("api_key")
                             var apiKey: String?, @field:Expose
                             @field:SerializedName("user_id")
                             var userId: Long?, @field:Expose
                             @field:SerializedName("access_token")
                             var accessToken: String?)

    class PublicApiHeader @Inject
    constructor(@param:ApiInfo @field:Expose
                @field:SerializedName("api_key")
                var apiKey: String?)
}
