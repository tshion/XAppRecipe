package com.github.tshion.xapprecipe.webapp.pages.login_biometric

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.ACTION_BIOMETRIC_ENROLL
import android.provider.Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.tshion.xapprecipe.webapp.R

/**
 * 生体認証ログイン
 */
class MainFragment : Fragment(R.layout.page_login_biometric) {
    companion object {
        private const val AUTH = BIOMETRIC_WEAK or DEVICE_CREDENTIAL
        private const val REQUEST_CHECK = 1000
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<AppCompatButton>(R.id.page_login_biometric_action).setOnClickListener {
            when (BiometricManager.from(requireContext()).canAuthenticate(AUTH)) {
                BiometricManager.BIOMETRIC_SUCCESS -> {
                    doBiometric()
                }
                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                    Intent(ACTION_BIOMETRIC_ENROLL).apply {
                        putExtra(EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED, AUTH)
                    }.also { startActivityForResult(it, REQUEST_CHECK) }
                }
                else -> {
                    Toast.makeText(requireContext(), "生体認証は使えません", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            REQUEST_CHECK -> doBiometric()
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }


    private fun doBiometric() {
        val biometricPrompt = BiometricPrompt(
            this,
            ContextCompat.getMainExecutor(requireContext()),
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(requireContext(), "認証エラー: ${errorCode}", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(requireContext(), "認証失敗", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(requireContext(), "認証成功: ${result}", Toast.LENGTH_SHORT).show()
                }
            }
        )

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setAllowedAuthenticators(AUTH)
            .setSubtitle("Log in using your biometric credential")
            .setTitle("Biometric login for my app")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}
