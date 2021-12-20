package com.example.testwebmotors.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testwebmotors.databinding.ItemLoadingStateBinding
import com.example.testwebmotors.utils.visible

/**
 * Adapter class to Load Vehicles
 *
 * @author: Marco Willy
 * @since: 20/12/2021
 **/

class VehiclesLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<VehiclesLoadStateAdapter.VehicleLoadStateViewHolder>() {

    inner class VehicleLoadStateViewHolder(
        private val binding: ItemLoadingStateBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.textViewError.text = loadState.error.localizedMessage
            }

            binding.progressbar.visible(loadState is LoadState.Loading)
            binding.buttonRetry.visible(loadState is LoadState.Error)
            binding.textViewError.visible(loadState is LoadState.Error)
            binding.buttonRetry.setOnClickListener {
                retry()
            }

            binding.progressbar.visibility = View.VISIBLE
        }
    }

    override fun onBindViewHolder(holder: VehicleLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = VehicleLoadStateViewHolder(
        ItemLoadingStateBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        retry
    )
}