package com.github.mdfh.flick.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import com.github.mdfh.flick.EventObserver
import com.github.mdfh.flick.R
import com.github.mdfh.flick.databinding.MovieListFragmentBinding
import com.github.mdfh.flick.model.api.Movie
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.movie_list_fragment.*
import javax.inject.Inject


class MovieListFragment : DaggerFragment() {

    private lateinit var viewDataBinding: MovieListFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var adapter: MovieListAdapter

    lateinit var skeleton: RecyclerViewSkeletonScreen

    private val args: MovieListFragmentArgs by navArgs()

    private val viewModel by viewModels<MovieListViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.movie_list_fragment, container, false)
        viewDataBinding = MovieListFragmentBinding.bind(root).apply {
            this.viewmodel = viewModel
        }
        // Set the lifecycle owner to the lifecycle of the view
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        viewModel.start(args.movieType)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = MovieListAdapter(viewModel);
        rv_movie_list.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_movie_list.adapter = adapter
        /*skeleton =
            getSkeletonRecyclerView(rv_movie_list, adapter, R.layout.item_skeleton_movie)*/

        setupNavigation()
    }

    private fun getSkeletonRecyclerView(
        recyclerView: RecyclerView,
        adapter: MovieListAdapter,
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

        viewModel.movies.observe(viewLifecycleOwner, Observer {
            adapter.addItems(it)
        })

        viewModel.openMovieEvent.observe(viewLifecycleOwner, EventObserver{
            openMovieDetail(it)
        })
    }

    private fun openMovieDetail(movie: Movie) {
        val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movie)
        findNavController().navigate(action)
    }

}
