package com.github.mdfh.flick.di.module

import com.github.mdfh.flick.data.pref.AppPrefRepository
import com.github.mdfh.flick.data.remote.ApiRepository
import com.github.mdfh.flick.data.remote.AppApiRepository
import com.github.mdfh.flick.data.remote.services.MovieService
import com.github.mdfh.flick.data.repository.MovieRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    internal fun provideMovieRepository(pref: AppPrefRepository, apiRepository: AppApiRepository): MovieRepository {
        return MovieRepository(pref, apiRepository);
    }
}