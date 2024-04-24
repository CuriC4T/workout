package com.curic4t.android.workout.view.main.ui.address

import android.content.Intent
import android.net.http.SslError
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.curic4t.android.workout.databinding.ActivitySearchAddressBinding
import com.curic4t.android.workout.view.main.ui.main.LoadingActivity
import com.curic4t.android.workout.view.main.ui.main.MainActivity
import com.curic4t.android.workout.view.main.ui.utils.LogUtil


class SearchAddressActivity : AppCompatActivity() {

    private var _binding: ActivitySearchAddressBinding? = null
    private val binding get() = _binding!!
    //R.layout.activity_search_address


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val webView = binding.webView

        webView.clearCache(true)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.addJavascriptInterface(DaumAddressJavaScriptInterface(), "Android")
        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler,
                error: SslError?
            ) {
                handler.proceed() // SSL 에러가 발생해도 계속 진행
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                view.loadUrl(url!!)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                webView.loadUrl("javascript:sample2_execDaumPostcode();")
            }
        }
        webView.loadUrl("https://azunyan.co.kr:8081/addresstest.html")
        //webView.loadUrl("file:///android_asset/addresstest.html")


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    inner class DaumAddressJavaScriptInterface {
        @JavascriptInterface
        fun processDATA(postCode: String, fullRoadAddr: String, jibunAddr: String) {
            val intent = Intent(this@SearchAddressActivity, MainActivity::class.java)
            intent.putExtra("postCode", postCode)
            intent.putExtra("fullRoadAddr", fullRoadAddr)
            intent.putExtra("jibunAddr", jibunAddr)
            LogUtil.d("jibun", postCode)
            LogUtil.d("jibun", jibunAddr)
            LogUtil.d("jibun", fullRoadAddr)
            setResult(RESULT_OK, intent)
            //startActivity(intent)
            finish()
        }
    }


}

