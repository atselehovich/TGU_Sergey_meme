package org.gutmann.memeview.ui

import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import org.gutmann.memeview.utils.BiometricPromptUtils.createBiometricPrompt
import org.gutmann.memeview.utils.getActivity

@Composable
fun BiometricPromptDialog(
    promptInfo: BiometricPrompt.PromptInfo,
    onSuccess: (BiometricPrompt.AuthenticationResult) -> Unit
) {
    val activity = LocalContext.current.getActivity()

    val biometricPrompt = activity?.let { createBiometricPrompt(it, onSuccess) }!!
    LaunchedEffect(null) {
        biometricPrompt.authenticate(promptInfo)
    }
}