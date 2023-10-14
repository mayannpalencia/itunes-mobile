package com.mayann.itunesmobile.features.main

import android.content.Intent
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mayann.itunesmobile.R
import com.mayann.itunesmobile.databinding.ActivityMainBinding
import com.mayann.itunesmobile.extensions.hideSoftKeyboard
import com.mayann.itunesmobile.extensions.prettyFormatTime
import com.mayann.itunesmobile.extensions.setCustomColorSchemeColors
import com.mayann.itunesmobile.extensions.setRefreshState
import com.mayann.itunesmobile.extensions.setVisible
import com.mayann.itunesmobile.extensions.textChanged
import com.mayann.itunesmobile.features.base.BaseActivity
import com.mayann.itunesmobile.features.detail.DetailActivity
import com.mayann.itunesmobile.features.detail.DetailActivity.Companion.BUNDLE_KEY_TRACK_ID
import com.mayann.itunesmobile.features.main.adapter.TrackAdapter
import com.mayann.persistence.features.favorite.TrackFavorite
import kotlinx.coroutines.launch


/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @use This activity displays the master list of the response from Itunes Search API
 * @desc Mobile Developer
 * @since 1.0
 */
class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {

    private val viewModel: MainViewModel by viewModels()

    private val trackAdapter: TrackAdapter by lazy {
        TrackAdapter(
            onTrackSelect = { track ->
                // callback for selecting and opening detail page to show track details
                selectTrack(track = track)
            },
            saveFavorite = { trackId ->
                // callback for saving of favorite from adapter
                viewModel.saveFavorite(trackId)
            },
            removeFavorite = { trackId ->
                // callback for removing a favorite from adapter
                viewModel.removeFavorite(trackId)
            }
        )
    }

    // Using coroutine flow to observe the state of the requests from the view model
    override fun setUpObserver() {
        super.setUpObserver()
        lifecycleScope.launch {
            viewModel.mainState.collect { state ->
                when (state) {
                    is MainState.ShowLoading -> {
                        showLoading(state.show)
                    }

                    is MainState.ShowTracks -> {
                        showTracks(state.tracks)
                    }

                    is MainState.ShowDetailPage -> {
                        showDetailPage(state.trackId)
                    }

                    is MainState.ShowLastDateVisited -> {
                        showLastDateVisited(state.date)
                    }

                    is MainState.ShowLastScreenVisited -> {
                        showLastScreenVisited(state.className)
                    }

                    is MainState.ShowError -> {

                    }
                }
            }
        }
    }

    // Run request for searching tracks using the declared default term
    // This will only run when activity is created then will be saved in local database
    override fun loadData() {
        super.loadData()
        viewModel.searchTracks(query = DEFAULT_TERM)
    }

    override fun onResume() {
        super.onResume()
        // Get tracks from local database
        // Every time the activity resumes, the app will not perform any network requests but instead will fetch persistent data saved locally
        viewModel.getTracks()
        viewModel.getLastScreenVisited()

        viewModel.saveLastDateVisited()
        viewModel.saveLastScreenVisited(javaClass.name)
    }

    override fun setUpView() {
        super.setUpView()

        window?.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.colorPrimaryDark)

        binding.layoutContentList.apply {
            recyclerViewTrack.layoutManager =
                LinearLayoutManager(this@MainActivity)
            recyclerViewTrack.adapter = trackAdapter

            swipeRefreshLayout.setCustomColorSchemeColors(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.colorAccent
                )
            )
        }
        viewModel.getLastDateVisited()
    }

    override fun addEventListener() {
        super.addEventListener()

        binding.apply {

            // Refresh listener
            // Passing the default term `star` for empty search
            layoutContentList.swipeRefreshLayout.setOnRefreshListener {
                val term = layoutHeader.editTextTerm.text.toString()
                viewModel.searchTracks(term.ifEmpty { DEFAULT_TERM })
            }

            // Search term change listeners
            // An edit text extension is used here to remove boiler plates from the main activity
            layoutHeader.editTextTerm.textChanged(
                afterTextChanged = { s ->
                    if (s.isNullOrEmpty()) {
                        viewModel.searchTracks(query = DEFAULT_TERM)
                    } else {
                        viewModel.searchTracks(s.toString())
                    }

                    hideSoftKeyboard()
                }
            )
        }
    }

    // This will update the loading states of the screen
    // Shimmer layout is used on this screen for better state visibility
    private fun showLoading(show: Boolean) {
        binding.layoutContentList.apply {

            recyclerViewTrack.setVisible(visible = !show, resize = false)

            layoutShimmer.shimmerLayout.setVisible(visible = show, resize = false)

            if (show) {
                layoutShimmer.shimmerLayout.startShimmer()
            } else {
                layoutShimmer.shimmerLayout.stopShimmer()
                swipeRefreshLayout.setRefreshState(false)
            }

        }
    }

    // Update UI to show the last date visited of the user
    private fun showLastDateVisited(date: String) {
        binding.layoutHeader.textLastVisited.apply {
            setVisible(visible = date.isBlank().not(), resize = true)

            if (date.isBlank().not()) {
                text = getString(R.string.last_visit, date.prettyFormatTime())
            }
        }
    }

    // Redirect the activity to the last saved screen once the app resumes
    private fun showLastScreenVisited(className: String) {
        if ((className != MainActivity::class.java.name) && className.isNotEmpty()) {
            try {
                startActivity(Intent(this@MainActivity, Class.forName(className)))
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }
        }
    }

    // Attached tracks to the adapter for displaying
    // This will also update the UI for empty states
    private fun showTracks(tracks: List<TrackFavorite>) {
        trackAdapter.submitList(tracks)

        binding.layoutContentList.apply {
            recyclerViewTrack.setVisible(visible = tracks.isNotEmpty(), resize = false)
            emptyView.setVisible(
                visible = tracks.isEmpty() && layoutShimmer.shimmerLayout.isInvisible,
                resize = false
            )
        }
    }

    // Opens the Detail activity and pass the track Id selected by the user.
    // The details of the track will be fetched from the Room local database using the track id.
    private fun showDetailPage(trackId: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(BUNDLE_KEY_TRACK_ID, trackId)
        startActivity(intent)
    }

    // Set last selected track id from user's on click event
    private fun selectTrack(track: TrackFavorite) {
        viewModel.selectTrack(trackId = track.id)
    }

    companion object {
        const val DEFAULT_TERM = "star"
    }

}