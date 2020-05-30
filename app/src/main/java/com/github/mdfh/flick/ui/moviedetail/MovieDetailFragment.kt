package com.github.mdfh.flick.ui.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.github.mdfh.flick.EventObserver
import com.github.mdfh.flick.R
import com.github.mdfh.flick.databinding.MovieDetailFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieDetailFragment : DaggerFragment() {

    private lateinit var viewDataBinding: MovieDetailFragmentBinding

    private val args: MovieDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MovieDetailViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.movie_detail_fragment, container, false)
        viewDataBinding = MovieDetailFragmentBinding.bind(root).apply {
            this.viewmodel = viewModel
        }
        viewModel.start(args.movie)
        // Set the lifecycle owner to the lifecycle of the view
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        return root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupNavigation()
    }

    private fun setupNavigation() {
        viewModel.movieInitializedCommand.observe(viewLifecycleOwner, EventObserver {
            viewDataBinding.apply {
                movie = it
                executePendingBindings()
            }
        })
    }

}
