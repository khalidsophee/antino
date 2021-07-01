package com.myproject.antinolabs.ui

import android.os.Bundle
import android.webkit.WebSettings.PluginState
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.myproject.antinolabs.BuildConfig
import com.myproject.antinolabs.databinding.ActivityWebBinding


class WebActivity : AppCompatActivity() {

    private val binding: ActivityWebBinding by lazy {
        ActivityWebBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.root

        val theWebPage = WebView(this)
        theWebPage.settings.javaScriptEnabled = true
        theWebPage.settings.pluginState = PluginState.ON
        setContentView(theWebPage)
        theWebPage.loadUrl(BuildConfig.BASE_URL)

    }
}