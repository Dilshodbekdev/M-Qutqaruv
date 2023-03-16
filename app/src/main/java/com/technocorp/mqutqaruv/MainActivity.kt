package com.technocorp.mqutqaruv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.GoogleMap
import com.technocorp.mqutqaruv.ui.theme.MulteeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MulteeTheme {
                GoogleMap(
                    modifier = Modifier.fillMaxSize( )
                )
            }
        }
    }
}