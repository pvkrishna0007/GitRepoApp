package com.mobile.gitrepoapp.app

import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {

    open fun getPageTitle() = ""

    override fun onResume() {
        super.onResume()

        activity?.title = getPageTitle()
    }

}