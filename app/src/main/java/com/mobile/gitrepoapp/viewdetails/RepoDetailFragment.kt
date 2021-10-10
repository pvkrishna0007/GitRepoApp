package com.mobile.gitrepoapp.viewdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mobile.gitrepoapp.R
import com.mobile.gitrepoapp.api.Repository
import com.mobile.gitrepoapp.api.Status
import com.mobile.gitrepoapp.api.response.RepoDetailModel
import com.mobile.gitrepoapp.app.BaseFragment
import com.mobile.gitrepoapp.databinding.FragmentRepoDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// adb shell am start -a android.intent.action.VIEW -d "http://www.example.com/repoDetails/pvkrishna0007/GitRepoApp"
@AndroidEntryPoint
class RepoDetailFragment: BaseFragment() {

    private val safeArgs: RepoDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentRepoDetailsBinding

    override fun getPageTitle() = getString(R.string.repository_details)

    @Inject
    lateinit var repository: Repository

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

        getRepoDetails()
    }

    private fun getRepoDetails() {
        val userName = safeArgs.userName
        val repositoryName = safeArgs.repositoryName

        Log.e("RepoDetailFragment", "getRepoDetails: UserName:$userName  Repo:$repositoryName")

        repository.getRepositoryDetailsByPath(userName, repositoryName).observe(
            viewLifecycleOwner,
            { apiResponse ->
                when (apiResponse.status) {
                    Status.LOADING -> {
                        Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        setUI(apiResponse.data)
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, apiResponse.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }

    private fun setUI(repoDetails: RepoDetailModel?) {
        binding.repoDetails = repoDetails

        binding.tvRepoPath.setOnClickListener {
            val action = RepoDetailFragmentDirections.actionRepoDetailFragmentToRepoWebDetailsFragment(
                repoDetails?.fullName?:"")
            findNavController().navigate(action)
        }
    }

}