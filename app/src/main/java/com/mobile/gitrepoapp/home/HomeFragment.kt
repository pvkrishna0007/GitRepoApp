package com.mobile.gitrepoapp.home

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.gitrepoapp.api.Repository
import com.mobile.gitrepoapp.api.Status
import com.mobile.gitrepoapp.app.BaseFragment
import com.mobile.gitrepoapp.databinding.FragmentHomeBinding
import com.mobile.gitrepoapp.utils.hideSoftKeyboard
import com.mobile.gitrepoapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment: BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var repository: Repository
    @Inject
    lateinit var repoAdapter: RepoAdapter
    @Inject
    lateinit var homeViewModel: HomeViewModel

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

        searchUserRepositories()

        binding.tvSearch.setEndIconOnClickListener {
            // handle end search icon action click
            it.hideSoftKeyboard()
            searchUserRepositories()
        }

        binding.etSearch.setOnEditorActionListener { view, actionId, _ ->
            // handle device keyboard action DONE button click
            if ((actionId == EditorInfo.IME_ACTION_DONE) or (actionId == KeyEvent.ACTION_DOWN)) {
                view.hideSoftKeyboard()
                searchUserRepositories()
            }
            true
        }

        binding.rvRepo.apply {
            adapter = repoAdapter
        }

    }

    private fun searchUserRepositories() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            homeViewModel.getRepositoryResultsFlow().collectLatest { pagingData ->
                repoAdapter.submitData(pagingData)
            }
        }
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

}