package com.mayann.itunesmobile.features.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mayann.itunesmobile.R
import com.mayann.itunesmobile.databinding.ItemListBinding
import com.mayann.persistence.features.favorite.TrackFavorite
import javax.inject.Inject


/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
class TrackAdapter @Inject constructor(
    private val onTrackSelect: (TrackFavorite) -> Unit,
    private val saveFavorite: (String) -> Unit,
    private val removeFavorite: (String) -> Unit,
) : ListAdapter<TrackFavorite, TrackAdapter.TrackViewHolder>(
    TrackComparator
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TrackViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: TrackAdapter.TrackViewHolder, position: Int) {
        getItem(position).let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class TrackViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(track: TrackFavorite) = with(binding) {

            Glide
                .with(root.context)
                .load(track.artwork.orEmpty())
                .placeholder(R.drawable.image_placeholder)
                .centerInside()
                .into(imageArtWork)

            textTrackName.text = track.name.orEmpty()

            textGenre.text = track.genre.orEmpty()

            textPrice.text = String.format("%s %.2f", track.currency.orEmpty(), track.price ?: 0.0)

            imageFavorite.updateFavoriteState(root.context, track)

            layoutDetail.setOnClickListener {
                onTrackSelect(track)
            }

            imageArtWork.setOnClickListener {
                onTrackSelect(track)
            }

            imageFavorite.setOnClickListener {
                if (track.favoriteCount > 0) {
                    removeFavorite(track.id)
                    track.favoriteCount = 0
                } else {
                    saveFavorite(track.id)
                    track.favoriteCount = 1
                }

                imageFavorite.updateFavoriteState(root.context, track)
            }
        }

        private fun ImageView.updateFavoriteState(context: Context, track: TrackFavorite) {

            setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    if (track.favoriteCount > 0) R.drawable.ic_favorite_on else R.drawable.ic_favorite_off
                )
            )
        }
    }

    object TrackComparator : DiffUtil.ItemCallback<TrackFavorite>() {
        override fun areItemsTheSame(oldItem: TrackFavorite, newItem: TrackFavorite) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TrackFavorite, newItem: TrackFavorite) =
            oldItem == newItem
    }
}


