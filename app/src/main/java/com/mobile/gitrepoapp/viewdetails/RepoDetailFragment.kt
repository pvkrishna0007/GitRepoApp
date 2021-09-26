package com.mobile.gitrepoapp.viewdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.gitrepoapp.api.response.RepoDetailModel
import com.mobile.gitrepoapp.app.BaseFragment
import com.mobile.gitrepoapp.databinding.FragmentRepoDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoDetailFragment: BaseFragment() {

    private lateinit var binding: FragmentRepoDetailsBinding

    override fun getPageTitle() = "Repository Details"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repoDetails = requireArguments().getParcelable<RepoDetailModel>("model")
        binding.repoDetails = repoDetails

    }

}