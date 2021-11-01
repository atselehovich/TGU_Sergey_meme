package org.gutmann.memeview

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import dagger.hilt.android.AndroidEntryPoint
import org.gutmann.memeview.navigation.MemeViewAppUI
import org.gutmann.memeview.ui.theme.MemeViewTheme

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemeViewTheme {
                Scaffold(content = { MemeViewAppUI() })
            }
        }
    }
}