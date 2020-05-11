package com.github.mdfh.magentosample.ui.splash

import androidx.lifecycle.ViewModel
import com.github.mdfh.magentosample.data.AppDataRepository
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val repository: AppDataRepository
) : ViewModel() {
}
