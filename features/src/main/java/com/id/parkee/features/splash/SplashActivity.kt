package com.id.parkee.features.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.id.parkee.commons.navigation.DashboardNavigation
import com.id.parkee.features.databinding.ActivitySplashBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : ScopeActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val splashViewModel: SplashViewModel by viewModel()

    private val dashboardNavigation: DashboardNavigation by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splashViewModel.saveToken(TOKEN)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(dashboardNavigation.createIntent(this))
        }, 1000)
    }

    companion object {
        private const val TOKEN =
            "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjMjcwOTdhNzllZDVlMzAyZDcxNDU1MWZlNjFhNjY2NyIsInN1YiI6IjY0ODY5YjhlZTM3NWMwMDBjNTI4YTJmZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.3qqiQQkqhV-JKvTkgTngmDOo57bcS_l1pEHL0KTI6hs"
    }
}
