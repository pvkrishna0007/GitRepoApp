package com.mobile.gitrepoapp.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mobile.gitrepoapp.api.Repository
import com.mobile.gitrepoapp.api.Status
import com.mobile.gitrepoapp.app.BaseFragment
import com.mobile.gitrepoapp.databinding.FragmentHomeBinding
import com.mobile.gitrepoapp.utils.hideSoftKeyboard
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment: BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var repository: Repository

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
            it.hideSoftKeyboard()

            searchUserRepositories()
        }

    }

    private fun searchUserRepositories() {
        repository.getRepositories().observe(viewLifecycleOwner, {
            Log.d("TAG", "onCreate: $it")
            when (it.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    Toast.makeText(context, "Msg:${it.data?.size}", Toast.LENGTH_LONG).show()
                }
                Status.ERROR -> {
                    Toast.makeText(context, "Err:${it.message}", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

}