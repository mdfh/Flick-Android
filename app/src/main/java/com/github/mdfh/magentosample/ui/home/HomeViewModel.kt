package com.github.mdfh.magentosample.ui.home

import androidx.lifecycle.ViewModel
import com.github.mdfh.magentosample.data.AppDataRepository
import javax.inject.Inject

class HomeViewModel  @Inject constructor(
    private val repository: AppDataRepository
) : ViewModel() {
}
