package com.github.mdfh.flick.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mdfh.flick.Event
import com.github.mdfh.flick.data.repository.AppDataRepository
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val repository: AppDataRepository
) : ViewModel() {

    private val _initializedCommand = MutableLiveData<Event<Unit>>()
    val initializedCommand: LiveData<Event<Unit>> = _initializedCommand

    init {
        _initializedCommand.value = Event(Unit)
    }
}
