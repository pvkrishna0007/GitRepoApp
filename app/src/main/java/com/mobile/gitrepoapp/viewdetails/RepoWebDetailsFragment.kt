package com.mobile.gitrepoapp.viewdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.mobile.gitrepoapp.app.BaseFragment
import com.mobile.gitrepoapp.databinding.FragmentProjectDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RepoWebDetailsFragment: BaseFragment() {

    override fun getPageTitle() = "Project Details"
    private lateinit var binding: FragmentProjectDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProjectDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webUrl = arguments?.getString("webUrl")?: "https://github.com/pvkrishna0007/GitRepoApp"

        binding.wvContent.settings.javaScriptEnabled = true
        binding.wvContent.settings.setAppCacheEnabled(true)
        binding.wvContent.settings.loadWithOverviewMode = true
        binding.wvContent.settings.useWideViewPort = true
        binding.wvContent.settings.builtInZoomControls = true
        binding.wvContent.settings.pluginState = WebSettings.PluginState.ON

        binding.wvContent.webViewClient = MyWebClient()

        binding.wvContent.loadUrl(webUrl)

    }

    class MyWebClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }

}