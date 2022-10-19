package com.trungdang.android.spaceexplorer.ui.home

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.trungdang.android.spaceexplorer.R
import com.trungdang.android.spaceexplorer.ui.component.video.VideoPlayer
import com.trungdang.android.spaceexplorer.ui.theme.SpaceExplorerTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate()")

        setSystemBarsBackground(this)

        setContent {
            ExploreTabPage()
        }

    }
}

private fun setSystemBarsBackground(activity: ComponentActivity) {
    //Set the status bar and navigation bar like YouTube app.
    when (activity.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES -> {
            activity.setTheme(R.style.Theme_SpaceExplorerDark)
        }
        Configuration.UI_MODE_NIGHT_NO -> {
            activity.setTheme(R.style.Theme_SpaceExplorerLight)
            val controller: WindowInsetsControllerCompat = WindowCompat.getInsetsController(activity.window, activity.window.decorView)
            controller.apply {
                isAppearanceLightNavigationBars = true
                isAppearanceLightStatusBars = true
            }
        }
    }
}

@Composable
fun ExploreTabPage() {
    SpaceExplorerTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            VideoPlayer(
                videoUri = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                isTesting = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SpaceExplorerTheme {
        VideoPlayer(isTesting = true)
    }
}