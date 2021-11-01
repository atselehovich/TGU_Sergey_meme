package org.gutmann.memeview.navigation

import androidx.biometric.BiometricPrompt
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.gutmann.memeview.R
import org.gutmann.memeview.ui.BiometricPromptDialog
import org.gutmann.memeview.ui.MemeInfoScreen
import org.gutmann.memeview.ui.MemesListScreen

@ExperimentalMaterialApi
@Composable
fun MemeViewAppUI() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "biometric_auth") {
        composable(route = "biometric_auth") {
            BiometricPromptDialog(
                promptInfo = BiometricPrompt.PromptInfo.Builder().apply {
                    val context = LocalContext.current

                    setTitle(context.getString(R.string.prompt_info_title))
                    setSubtitle(context.getString(R.string.prompt_info_subtitle))
                    setDescription(context.getString(R.string.prompt_info_description))
                    setConfirmationRequired(false)
                    setNegativeButtonText(context.getString(R.string.prompt_info_use_app_password))
                }.build(),
                onSuccess = {
                    // navigate to list
                    navController.navigate("memes_list") {
                        // and remove this screen from back stack to
                        // avoid going back after we already pass auth process
                        popUpTo("biometric_auth") { inclusive = true }
                    }
                })
        }
        composable(route = "memes_list") {
            MemesListScreen(navigateToMeme = { memeId ->
                navController.navigate("meme?id=$memeId")
            })
        }
        composable(
            route = "meme?id={memeId}",
            arguments = listOf(navArgument("memeId") { defaultValue = -1 })
        ) { backStackEntry ->
            val memeId = backStackEntry.arguments?.getInt("memeId")
            memeId?.let { MemeInfoScreen(memeId = it) }
        }
    }
}