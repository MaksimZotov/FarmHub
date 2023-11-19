package ru.rshbdigital.farmhub.main

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.rshbdigital.farmhub.R
import ru.rshbdigital.farmhub.client.login.LoginRepository
import ru.rshbdigital.farmhub.client.offline.OfflineRepository
import ru.rshbdigital.farmhub.core.ui.components.OfflineListener
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity(), OfflineListener {

    @Inject
    lateinit var offlineRepository: OfflineRepository

    @Inject
    lateinit var loginRepository: LoginRepository

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted.not()) {
            Toast.makeText(
                this,
                R.string.notification_explanation,
                Toast.LENGTH_LONG,
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val userRole by loginRepository.userRole.collectAsState()
            AppScreen(userRole)
        }
        createNotificationChannel()
        askNotificationPermission()
        sendFcmToken()
    }

    override val isOffline get() = offlineRepository.isOffline

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = getString(R.string.default_notification_channel_id)
            val channelName = getString(R.string.default_notification_channel_title)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(
                NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_LOW,
                ),
            )
        }
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permissionResult = ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
            if (permissionResult != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun sendFcmToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                lifecycleScope.launch {
                    loginRepository.sendFcmToken(task.result)
                }
            }
        }
    }
}
