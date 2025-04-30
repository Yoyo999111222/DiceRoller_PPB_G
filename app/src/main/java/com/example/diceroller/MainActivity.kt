package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme
import kotlinx.coroutines.delay
import androidx.compose.animation.core.tween
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.animation.core.animateFloatAsState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiceRollerApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    val density = LocalDensity.current.density
    var result by remember { mutableIntStateOf(1) }
    var isRolling by remember { mutableStateOf(false) }
    var imageResource by remember { mutableIntStateOf(R.drawable.dice_1) }

    // Untuk animasi rotasi 3D
    var rotation by remember { mutableFloatStateOf(0f) }
    val animatedRotation by animateFloatAsState(
        targetValue = rotation,
        animationSpec = tween(durationMillis = 600),
        label = "3D Rotation"
    )

    // Efek rolling
    LaunchedEffect(isRolling) {
        if (isRolling) {
            repeat(10) {
                result = (1..6).random()
                imageResource = when (result) {
                    1 -> R.drawable.dice_1
                    2 -> R.drawable.dice_2
                    3 -> R.drawable.dice_3
                    4 -> R.drawable.dice_4
                    5 -> R.drawable.dice_5
                    else -> R.drawable.dice_6
                }
                rotation += 60f
                delay(50)
            }
            isRolling = false
            rotation = 0f
        }
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = result.toString(),
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer(
                    rotationY = animatedRotation,
                    cameraDistance = 8 * density
                )
        )

        Text(
            text = "Kamu mendapat angka $result",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { if (!isRolling) isRolling = true },
            enabled = !isRolling
        ) {
            Text(
                text = if (isRolling) "Mengocok..." else stringResource(R.string.roll),
                fontSize = 24.sp
            )
        }
    }
}
