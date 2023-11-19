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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.rshbdigital.farmhub.client.login.LoginRepository
import ru.rshbdigital.farmhub.core.design.FarmHubTheme
import ru.rshbdigital.farmhub.core.ui.components.AlertDialog
import ru.rshbdigital.farmhub.core.ui.model.AlertDialogItem
import ru.rshbdigital.farmhub.core.ui.model.Text
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
                        dialog = AlertDialogItem(
                            title = Text.Simple("Авторизация"),
                            message = Text.Simple("Для начала работы поднесите RFID-карту к устройству"),
                            isDismissable = false,
                        ),
                    )
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
