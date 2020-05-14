/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.mdfh.flick.di.module

import androidx.lifecycle.ViewModel
import com.github.mdfh.flick.di.ViewModelBuilder
import com.github.mdfh.flick.di.ViewModelKey
import com.github.mdfh.flick.ui.splash.SplashFragment
import com.github.mdfh.flick.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Dagger module for the Statistics feature.
 */
@Module
abstract class SplashModule {

    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun statisticsFragment(): SplashFragment

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindViewModel(viewmodel: SplashViewModel): ViewModel
}
