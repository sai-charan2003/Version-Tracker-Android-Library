package dev.charan.versionTracker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import dev.charan.versionTracker.ui.theme.VersionTrackerTheme
import dev.charan.versiontracker.VersionTracker
import dev.charan.versiontracker.model.ProcessState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VersionTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(

                    )
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        launch {
            VersionTracker.getLatestAppVersion("Battery Tracker","340f4393-f3be-4049-857c-7c490f7f4591").observeForever {
                when(it){
                    is ProcessState.Error -> {

                    }
                    ProcessState.Loading -> {

                    }
                    is ProcessState.Success -> {
                        Log.d("TAG", "Greeting: ${it.autoUpdateDTO}")
                    }
                }

            }
        }

    }


}

