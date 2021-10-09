package com.mobile.gitrepoapp.home

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mobile.gitrepoapp.R
import com.mobile.gitrepoapp.app.BaseFragment
import com.mobile.gitrepoapp.databinding.FragmentHomeBinding
import com.mobile.gitrepoapp.utils.hideSoftKeyboard
import com.mobile.gitrepoapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

/**
 * If you want to load the list of repositories with the name(eg:River) with deep link.
 *  adb shell am start -a android.intent.action.VIEW -d "http://www.example.com/search/River"
 *  adb shell am start -a android.intent.action.VIEW -d "http://www.example.com/search/pvkrishna0007/GitRepoApp"
 */
@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class HomeFragment: BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var repoAdapter: RepoAdapter
    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun getPageTitle() = getString(R.string.home)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        arguments?.getString("repoName")?.let {
            homeViewModel.queryFlow.value = it // Repo Name here
        }
        binding.homeViewModel = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvSearch.setEndIconOnClickListener {
            // handle end search icon action click
            it.hideSoftKeyboard()
            setSearchHintView(false)
        }

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            // handle device keyboard action DONE button click
            if ((actionId == EditorInfo.IME_ACTION_DONE) or (actionId == KeyEvent.ACTION_DOWN)) {
                view.hideSoftKeyboard()
                setSearchHintView(false)
            }
            true
        }

        binding.rvRepo.apply {
            adapter = repoAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            //To set data to the adapter
            homeViewModel.repositoryResultsFlow.collectLatest { pagingData ->
                repoAdapter.submitData(pagingData)
            }
        }

        repoAdapter.itemClickCallback { repoDetailModel ->
            findNavController().navigate(R.id.action_homeFragment_to_repoDetailFragment, Bundle().apply {
                putString("userName", repoDetailModel.owner?.login)
                putString("repositoryName", repoDetailModel.name)
            })
        }

    }

    private fun setSearchHintView(isVisible: Boolean) {
        binding.tvMessage.isVisible = isVisible
    }

}