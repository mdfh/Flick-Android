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
import com.github.mdfh.flick.ui.home.HomeFragment
import com.github.mdfh.flick.ui.home.HomeViewModel
import com.github.mdfh.flick.ui.moviedetail.MovieDetailFragment
import com.github.mdfh.flick.ui.moviedetail.MovieDetailViewModel
import com.github.mdfh.flick.ui.movielist.MovieListFragment
import com.github.mdfh.flick.ui.movielist.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Dagger module for the Home feature.
 */
@Module
abstract class MovieListModule {

    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun movieListFragment(): MovieListFragment

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun bindMovieListViewModel(viewmodel: MovieListViewModel): ViewModel
}
