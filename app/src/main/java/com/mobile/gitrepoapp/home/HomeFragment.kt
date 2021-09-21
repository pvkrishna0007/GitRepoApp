package com.mobile.gitrepoapp.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.mobile.gitrepoapp.api.Repository
import com.mobile.gitrepoapp.api.Status
import com.mobile.gitrepoapp.app.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment: BaseFragment() {

    @Inject
    lateinit var repository: Repository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository.getRepositories().observe(viewLifecycleOwner, Observer {
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