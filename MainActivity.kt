package com.sekolah.exambrow

import android.os.Bundle
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // GANTI alamat ini dengan link ujian kamu
    private val urlUjian = "https://example.com"

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Blok screenshot dan rekam layar, layar tetap menyala
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        webView = WebView(this)
        setContentView(webView)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl(urlUjian)

        // Tombol Back hanya mundur di halaman web, tidak keluar aplikasi
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack()) webView.goBack()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        // Kunci layar (screen pinning)
        try {
            startLockTask()
        } catch (e: Exception) {
        }
    }
}
