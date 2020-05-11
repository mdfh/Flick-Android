package com.github.mdfh.magentosample.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mdfh.magentosample.Event
import com.github.mdfh.magentosample.data.AppDataRepository
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val repository: AppDataRepository
) : ViewModel() {

    private val _initializedCommand = MutableLiveData<Event<Unit>>()
    val initializedCommand: LiveData<Event<Unit>> = _initializedCommand
}
