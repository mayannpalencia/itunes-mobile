package com.mayann.itunesmobile.features.detail

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.mayann.itunesmobile.R
import com.mayann.itunesmobile.databinding.ActivityDetailBinding
import com.mayann.itunesmobile.features.base.BaseActivity
import com.mayann.persistence.features.favorite.TrackFavorite
import kotlinx.coroutines.launch

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @use This activity displays the details of a {@link TrackFavorite} object
 * @desc Mobile Developer
 * @since 1.0
 */
class DetailActivity : BaseActivity<ActivityDetailBinding>(
    ActivityDetailBinding::inflate
) {

    private val viewModel: DetailViewModel by viewModels()

    private var trackId: String? = null
    private var currentTrack: TrackFavorite? = null

    // Using flow to observe the state of the requests from the view model within our coroutine scope
    override fun setUpObserver() {
        super.setUpObserver()
        lifecycleScope.launch {
            viewModel.detailState.collect { state ->
                when (state) {
                    is DetailState.SetTrackId -> {
                        trackId = state.trackId

                        viewModel.getTrackDetails(trackId)
                    }

                    is DetailState.ShowTrackDetail -> {
                        currentTrack = state.track
                        showTrackDetail(state.track)
                    }

                    is DetailState.ExitPage -> {
                        finish()
                    }

                    is DetailState.ShowError -> {
                        displayToast(message = getString(R.string.error))
                    }

                }
            }
        }
    }


    // Get intent extras passed by the parent activity
    override fun loadData() {
        super.loadData()
        viewModel.getIntentExtras(intent)
    }

    // Get the details of the track from local database
    override fun onResume() {
        super.onResume()
        viewModel.getTrackDetails(trackId)

        viewModel.saveLastScreenVisited(javaClass.name)
    }

    override fun addEventListener() {
        super.addEventListener()

        binding.apply {

            toolbar.setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            // Remove favorite if current favoriteCount is greater than 0, else add it as favorite
            // This listener will update the favorite UI as well
            imageFavorite.setOnClickListener {
                if ((currentTrack?.favoriteCount ?: 0) > 0) {
                    trackId?.let {
                        viewModel.removeFavorite(it)
                        currentTrack?.favoriteCount = 0
                    }
                } else {
                    trackId?.let {
                        viewModel.saveFavorite(it)
                        currentTrack?.favoriteCount = 1
                    }
                }

                imageFavorite.updateFavoriteState(this@DetailActivity, currentTrack)
            }

            onBackPressedDispatcher.addCallback(
                this@DetailActivity,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        viewModel.saveLastScreenVisited("")
                    }
                })
        }
    }

    private fun ImageView.updateFavoriteState(context: Context, track: TrackFavorite?) {
        setImageDrawable(
            ContextCompat.getDrawable(
                context,
                if ((track?.favoriteCount
                        ?: 0) > 0) R.drawable.ic_favorite_on else R.drawable.ic_favorite_off
            )
        )
    }

    // Update UI based on the value from {@link TrackFavorite}
    private fun showTrackDetail(track: TrackFavorite) {

        binding.apply {

            Glide
                .with(root.context)
                .load(track.artwork.orEmpty())
                .placeholder(R.drawable.image_placeholder)
                .centerInside()
                .into(imageArtWork)

            Glide
                .with(root.context)
                .load(track.artwork.orEmpty())
                .placeholder(R.drawable.image_placeholder)
                .centerCrop()
                .into(imageBackground)

            textTrackName.text = track.name.orEmpty()

            textGenre.text = track.genre.orEmpty()

            textPrice.text = String.format("%s %.2f", track.currency.orEmpty(), track.price ?: 0.0)

            textDescription.text = track.longDescription.orEmpty()

            imageFavorite.updateFavoriteState(this@DetailActivity, track)
        }
    }

    // Display message for errors
    private fun displayToast(message: String) {
        Toast.makeText(this@DetailActivity, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val BUNDLE_KEY_TRACK_ID = "bundle_key_track_id"
    }
}