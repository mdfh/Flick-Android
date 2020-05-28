package com.github.mdfh.flick.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import com.github.mdfh.flick.EventObserver
import com.github.mdfh.flick.R
import com.github.mdfh.flick.databinding.HomeFragmentBinding
import com.github.mdfh.flick.model.api.Movie
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject


class HomeFragment : DaggerFragment() {

    private lateinit var viewDataBinding: HomeFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var popularAdapter: HomeAdapter
    lateinit var topRatedAdapter: HomeAdapter
    lateinit var upcomingAdapter: HomeAdapter
    lateinit var nowPlayingAdapter: HomeAdapter

    lateinit var popularSkeleton: RecyclerViewSkeletonScreen
    lateinit var topRatedSkeleton: RecyclerViewSkeletonScreen
    lateinit var upComingSkeleton: RecyclerViewSkeletonScreen
    lateinit var nowPlayingSkeleton: RecyclerViewSkeletonScreen

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

        popularAdapter = HomeAdapter(viewModel);
        rv_popular.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_popular.adapter = popularAdapter
        popularSkeleton =
            getSkeletonRecyclerView(rv_popular, popularAdapter, R.layout.item_skeleton_movie)


        topRatedAdapter = HomeAdapter(viewModel);
        rv_top_rated.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_top_rated.adapter = topRatedAdapter
        topRatedSkeleton =
            getSkeletonRecyclerView(rv_top_rated, topRatedAdapter, R.layout.item_skeleton_movie)

        upcomingAdapter = HomeAdapter(viewModel);
        rv_upcoming.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_upcoming.adapter = upcomingAdapter
        upComingSkeleton =
            getSkeletonRecyclerView(rv_upcoming, upcomingAdapter, R.layout.item_skeleton_movie)

        nowPlayingAdapter = HomeAdapter(viewModel, isCarousel = true);
        rv_now_playing.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_now_playing.adapter = nowPlayingAdapter
        nowPlayingSkeleton =
            getSkeletonRecyclerView(rv_now_playing, nowPlayingAdapter, R.layout.item_skeleton_movie_carousel)

        setupNavigation()
    }

    private fun getSkeletonRecyclerView(
        recyclerView: RecyclerView,
        adapter: HomeAdapter,
        itemLayout: Int
    ) =
        Skeleton.bind(recyclerView)
            .adapter(adapter)
            .shimmer(true)
            .angle(20)
            .frozen(false)
            .duration(1200)
            .count(10)
            .load(itemLayout)
            .show(); //default count is 10


    private fun setupNavigation() {
        viewModel.popularMovies.observe(viewLifecycleOwner, Observer {
            popularSkeleton.hide()
            popularAdapter.addItems(it)
        })

        viewModel.upcomingMovies.observe(viewLifecycleOwner, Observer {
            upComingSkeleton.hide()
            upcomingAdapter.addItems(it)
        })

        viewModel.topRatedMovies.observe(viewLifecycleOwner, Observer {
            topRatedSkeleton.hide()
            topRatedAdapter.addItems(it)
        })

        viewModel.nowPlaying.observe(viewLifecycleOwner, Observer {
            nowPlayingSkeleton.hide()
            nowPlayingAdapter.addItems(it)
        })

        viewModel.openMovieEvent.observe(viewLifecycleOwner, EventObserver {
            openMovieDetail(it)
        })

        viewModel.openMovieListEvent.observe(viewLifecycleOwner, EventObserver {
            openMovieList(it)
        })
    }

    private fun openMovieDetail(movie: Movie) {
        val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movie)
        findNavController().navigate(action)
    }

    private fun openMovieList(movieType: MovieType) {
        val action = HomeFragmentDirections.actionHomeFragmentToMovieListFragment(movieType)
        findNavController().navigate(action)
    }

}
