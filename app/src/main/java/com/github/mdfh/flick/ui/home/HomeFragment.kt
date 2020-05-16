package com.github.mdfh.flick.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mdfh.flick.R
import com.github.mdfh.flick.databinding.HomeFragmentBinding
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject


class HomeFragment : DaggerFragment() {

    private lateinit var viewDataBinding: HomeFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var popularAdapter : HomeAdapter
    lateinit var topRatedAdapter : HomeAdapter
    lateinit var upcomingAdapter : HomeAdapter
    lateinit var nowPlayingAdapter : HomeAdapter

    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.home_fragment, container, false)
        viewDataBinding = HomeFragmentBinding.bind(root).apply {
            this.viewmodel = viewModel
        }
        // Set the lifecycle owner to the lifecycle of the view
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        popularAdapter = HomeAdapter(requireActivity());
        rv_popular.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_popular.adapter = popularAdapter

        topRatedAdapter = HomeAdapter(requireActivity());
        rv_top_rated.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_top_rated.adapter = topRatedAdapter

        upcomingAdapter = HomeAdapter(requireActivity());
        rv_upcoming.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_upcoming.adapter = upcomingAdapter

        nowPlayingAdapter = HomeAdapter(requireActivity(), isCarousel = true);
        rv_now_playing.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_now_playing.adapter = nowPlayingAdapter

        setupNavigation()
    }

    private fun setupNavigation() {
        viewModel.popularMovies.observe(viewLifecycleOwner, Observer {
            popularAdapter.addItems(it)
        })

        viewModel.upcomingMovies.observe(viewLifecycleOwner, Observer {
            upcomingAdapter.addItems(it)
        })

        viewModel.topRatedMovies.observe(viewLifecycleOwner, Observer {
            topRatedAdapter.addItems(it)
        })

        viewModel.nowPlaying.observe(viewLifecycleOwner, Observer {
            nowPlayingAdapter.addItems(it)
        })
    }

}
