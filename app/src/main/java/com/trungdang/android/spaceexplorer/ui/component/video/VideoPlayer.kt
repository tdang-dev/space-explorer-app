package com.trungdang.android.spaceexplorer.ui.component.video

import android.R.color.holo_green_dark
import android.R.color.holo_purple
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import timber.log.Timber


@Composable
fun VideoPlayer(
    videoUri: String = "",
    isTesting: Boolean = false
) {
    val context = LocalContext.current
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    if (isTesting) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(color = colorResource(id = holo_green_dark))) {
            Text("Video player test.", modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth()
                .background(color = colorResource(id = holo_purple)))
        }
    } else {
        if (videoUri.isNotBlank()) {
            val player = remember {
                ExoPlayer.Builder(context).build().apply {
                    this.playWhenReady = true
                    this.setMediaItem(MediaItem.fromUri(videoUri))
                    this.prepare()
                }
            }


            DisposableEffect(
                AndroidView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    factory = {
                        StyledPlayerView(context).apply {
                            this.player = player
                        }
                    }
                )
            ) {
                val observer = LifecycleEventObserver { owner, event ->
                    when (event) {
                        Lifecycle.Event.ON_CREATE,
                        Lifecycle.Event.ON_START, Lifecycle.Event.ON_STOP-> {

                        }
                        Lifecycle.Event.ON_RESUME -> {
                            Timber.d("Received on resume lifecycle event.")
                            player.play()
                        }
                        Lifecycle.Event.ON_PAUSE -> {
                            Timber.d("Received on pause lifecycle event.")
                            player.pause()
                        }
                        Lifecycle.Event.ON_DESTROY -> {
                            player.release()
                        }
                        Lifecycle.Event.ON_ANY -> {
                            //Do nothing
                        }
                        else -> {
                            Timber.d("Received unknown lifecycle event.")
                        }
                    }
                }

                lifecycleOwner.value.lifecycle.addObserver(observer)

                onDispose {
                    player.release()
                }
            }
        }
    }
}
