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
import com.mobile.gitrepoapp.api.Repository
import com.mobile.gitrepoapp.app.BaseFragment
import com.mobile.gitrepoapp.databinding.FragmentHomeBinding
import com.mobile.gitrepoapp.utils.hideSoftKeyboard
import com.mobile.gitrepoapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.collectLatest
import okhttp3.Dispatcher
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class HomeFragment: BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var repository: Repository
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvSearch.setEndIconOnClickListener {
            // handle end search icon action click
            it.hideSoftKeyboard()
            searchUserRepositories()
            setSearchHintView(false)
        }

        binding.etSearch.setOnEditorActionListener { view, actionId, _ ->
            // handle device keyboard action DONE button click
            if ((actionId == EditorInfo.IME_ACTION_DONE) or (actionId == KeyEvent.ACTION_DOWN)) {
                view.hideSoftKeyboard()
                searchUserRepositories()
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

        repoAdapter.itemClickCallback() { repoDetailModel ->
            findNavController().navigate(R.id.action_homeFragment_to_repoDetailFragment, Bundle().apply {
                putParcelable("model", repoDetailModel)
            })
        }

    }

    private fun searchUserRepositories() {
        val searchQuery = binding.etSearch.text.toString().trim()
//        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
//            homeViewModel.getRepositoryResultsFlow(searchQuery).collectLatest { pagingData ->
//                repoAdapter.submitData(pagingData)
//            }
//        }
        homeViewModel.searchRepositories(searchQuery)
    }

    private fun setSearchHintView(isVisible: Boolean) {
        binding.tvMessage.isVisible = isVisible
    }
//    private fun searchUserRepositories() {
//        repository.getRepositories("pvkrishna0007").observe(viewLifecycleOwner, {
//            Log.d("TAG", "onCreate: $it")
//            when (it.status) {
//                Status.LOADING -> {
//                }
//                Status.SUCCESS -> {
//                    viewLifecycleOwner.lifecycleScope.launchWhenCreated {
//                        repoAdapter.submitData(it.data)
//                    }
//                    Toast.makeText(context, "Msg:${it.data?.size}", Toast.LENGTH_SHORT).show()
//                }
//                Status.ERROR -> {
//                    Toast.makeText(context, "Err:${it.message}", Toast.LENGTH_LONG).show()
//                }
//            }
//        })
//    }

//    @ExperimentalCoroutinesApi
//    @FlowPreview
//    private fun search() {
//        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
//            binding.etSearch.getQueryTextChangeStateFlow()
//                .debounce(300)
//                .filter { query ->
//                    return@filter query.isNotEmpty()
//                }
//                .distinctUntilChanged()
//                .flatMapLatest { query ->
//                    homeViewModel.getRepositoryResultsFlow(query)
//                }
//                .flowOn(Dispatchers.Default)
//                .collect { pagingData ->
//                    repoAdapter.submitData(pagingData)
//                }
//        }
//    }

}