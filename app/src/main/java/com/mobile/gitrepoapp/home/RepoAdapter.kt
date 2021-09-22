package com.mobile.gitrepoapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobile.gitrepoapp.api.response.RepoDetailModel
import com.mobile.gitrepoapp.databinding.RowRepoItemBinding
import javax.inject.Inject

class RepoAdapter @Inject constructor() :
    PagingDataAdapter<RepoDetailModel, RepoAdapter.RepoViewHolder>(RepoComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RepoViewHolder(
            RowRepoItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class RepoViewHolder(private val binding: RowRepoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RepoDetailModel) = with(binding) {
            repoItem = item
        }
    }

    object RepoComparator : DiffUtil.ItemCallback<RepoDetailModel>() {
        override fun areItemsTheSame(oldItem: RepoDetailModel, newItem: RepoDetailModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: RepoDetailModel, newItem: RepoDetailModel) =
            oldItem == newItem
    }
}