package com.github.mdfh.flick.ui.home

import androidx.lifecycle.ViewModel
import com.github.mdfh.flick.data.AppDataRepository
import javax.inject.Inject

class HomeViewModel  @Inject constructor(
    private val repository: AppDataRepository
) : ViewModel() {
}
