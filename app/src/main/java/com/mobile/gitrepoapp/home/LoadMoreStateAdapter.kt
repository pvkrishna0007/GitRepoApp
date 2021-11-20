package com.mobile.gitrepoapp.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobile.gitrepoapp.R
import com.mobile.gitrepoapp.databinding.LoadStateViewBinding

class LoadMoreStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<LoadMoreStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {

        val progress = holder.binding.loadStateProgress
        val btnRetry = holder.binding.loadStateRetry
        val txtErrorMessage = holder.binding.loadStateErrorMessage

        btnRetry.isVisible = loadState !is LoadState.Loading
        txtErrorMessage.isVisible = loadState !is LoadState.Loading
        progress.isVisible = loadState is LoadState.Loading

        if (loadState is LoadState.Error){
            txtErrorMessage.text = loadState.error.localizedMessage
        }

        btnRetry.setOnClickListener {
            retry.invoke()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.load_state_view, parent, false)
        )
    }

    class LoadStateViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val binding = LoadStateViewBinding.bind(view)
    }
}