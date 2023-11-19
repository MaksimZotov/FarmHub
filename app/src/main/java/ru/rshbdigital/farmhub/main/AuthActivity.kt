package ru.rshbdigital.farmhub.main

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.rshbdigital.farmhub.client.login.LoginRepository
import ru.rshbdigital.farmhub.core.design.FarmHubTheme
import ru.rshbdigital.farmhub.core.design.Typography
import ru.rshbdigital.farmhub.main.theme.DimenTokens
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {

    @Inject
    lateinit var loginRepository: LoginRepository

    private lateinit var writeTagFilters: Array<IntentFilter>
    private var nfcAdapter: NfcAdapter? = null
    private var pendingIntent: PendingIntent? = null

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startMainActivity()
        return
        lifecycleScope.launch {
            loginRepository.isAuthorized.collect { isAuthorized ->
                if (isAuthorized) startMainActivity()
            }
        }
        setContent {
            FarmHubTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(FarmHubTheme.background.get())
                ) {
                    AlertDialog(
                        properties = DialogProperties(
                            dismissOnBackPress = false,
                            dismissOnClickOutside = false,
                        ),
                        onDismissRequest = {}
                    ) {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = FarmHubTheme.surface.get(),
                            shape = RoundedCornerShape(DimenTokens.x6),
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(DimenTokens.x6)
                            ) {
                                Text(
                                    text = "Авторизация",
                                    style = Typography.h2,
                                    color = FarmHubTheme.onSurface.get(),
                                )
                                Spacer(modifier = Modifier.height(DimenTokens.x3))
                                Text(
                                    text = "Для начала работы с приложением поднесите RFID-карту к устройству",
                                    style = Typography.body1,
                                    color = FarmHubTheme.primary.get().copy(alpha = 0.6f),
                                )
                            }
                        }
                    }
                }
            }
        }

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if (nfcAdapter == null) {
            Toast.makeText(this, "Устройство не поддерживает NFC, авторизация невозможна", Toast.LENGTH_LONG).show()
        }

        readFromIntent(intent)
        pendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )
        val tagDetected = IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
        tagDetected.addCategory(Intent.CATEGORY_DEFAULT)
        writeTagFilters = arrayOf(tagDetected)
    }

    private fun readFromIntent(intent: Intent) {
        val action = intent.action
        if (NfcAdapter.ACTION_TAG_DISCOVERED == action || NfcAdapter.ACTION_TECH_DISCOVERED == action || NfcAdapter.ACTION_NDEF_DISCOVERED == action) {
            val tag = intent.getParcelableExtra<Parcelable>(NfcAdapter.EXTRA_TAG) as Tag?
            val tagId = tag?.id?.toHex()
            tagId?.let { handleTagScanned(tagId) }
        }
    }

    private fun ByteArray.toHex(): String {
        return joinToString(":") { "%02x".format(it) }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        readFromIntent(intent)
    }

    private fun handleTagScanned(tagId: String) {
        lifecycleScope.launch {
            try {
                loginRepository.loginByRfid(tagId)
            } catch (e: Exception) {
                Timber.e(e)
                Toast.makeText(this@AuthActivity, "Что-то пошло не так, попробуйте позже", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun startMainActivity() {
        startActivity(
            Intent(this@AuthActivity, MainActivity::class.java),
        )
        finish()
    }

    override fun onPause() {
        super.onPause()
        nfcAdapter?.disableForegroundDispatch(this)
    }

    override fun onResume() {
        super.onResume()
        nfcAdapter?.enableForegroundDispatch(this, pendingIntent, writeTagFilters, null)
    }
}
