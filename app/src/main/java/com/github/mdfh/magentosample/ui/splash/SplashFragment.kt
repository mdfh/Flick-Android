package com.github.mdfh.magentosample.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.mdfh.magentosample.EventObserver
import com.github.mdfh.magentosample.R
import com.github.mdfh.magentosample.databinding.SplashFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SplashFragment : DaggerFragment() {

    private lateinit var viewDataBinding: SplashFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<SplashViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.splash_fragment, container, false)
        viewDataBinding = SplashFragmentBinding.bind(root).apply {
            this.viewmodel = viewModel
        }
        // Set the lifecycle owner to the lifecycle of the view
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        return root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupNavigation()
    }

    private fun setupNavigation() {
        viewModel.initializedCommand.observe(viewLifecycleOwner, EventObserver {
            val action = SplashFragmentDirections
                .actionSplashFragmentToHomeFragment()

            findNavController().navigate(action)
        })
    }

}
