package com.kotlin.inicio


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kotlin.inicio.ui.theme.InicioTheme

class MainActivity : ComponentActivity() {
    private var currentScreen by mutableStateOf<Screens>(Screens.Splash)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InicioTheme {
                when (currentScreen) {
                    is Screens.Splash -> SplashScreen {
                        currentScreen = Screens.Intro
                    }
                    is Screens.Intro -> IntroScreen {
                        currentScreen = Screens.Login
                    }
                    is Screens.Login -> LoginScreen()
                }
            }
        }
    }
}

// Enum para gestionar las pantallas
sealed class Screens {
    object Splash : Screens()
    object Intro : Screens()
    object Login : Screens()
}

@Composable
fun SplashScreen(onContinueClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(183, 74, 212)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .clickable { onContinueClick() }
        ) {
            Spacer(modifier = Modifier.height(200.dp))
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 100.dp,
                            topEnd = 100.dp,
                            bottomEnd = 100.dp,
                            bottomStart = 100.dp
                        ))
            )
            Spacer(modifier = Modifier.height(100.dp))
            Text("", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(115.dp))
            Text(
                text = "Breyner Alexis Orguela Vargas",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                color = (Color.White),
                modifier = Modifier.padding(0.dp)
            )
        }
    }
}

@Composable
fun IntroScreen(onStartClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(183, 74, 212)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), // Reemplaza con tu imagen
                contentDescription = null,
                modifier = Modifier.size(150.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 100.dp,
                            topEnd = 100.dp,
                            bottomEnd = 100.dp,
                            bottomStart = 100.dp
                        ))// Tamaño de la imagen
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Bienvenido a nuestra app",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                color = (Color.White),
                modifier = Modifier.padding(0.dp)
            )
            Button(
                onClick = onStartClick, // Navega al Login
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors( Color(23, 112, 207))


            ) {
                Text("Comenzar")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onStartClick, // Navega al Login
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors( Color(23, 112, 207))

            ) {
                Text("Registrarse")
            }
        }
    }
}

@Composable
fun LoginScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginMessage by remember { mutableStateOf("") }
    Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(183, 74, 212)),

    )

    Image(

        painter = painterResource(id = R.drawable.logo), // Reemplaza con tu imagen
        contentDescription = null,
        modifier = Modifier
            .size(130.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 100.dp,
                    topEnd = 100.dp,
                    bottomEnd = 100.dp,
                    bottomStart = 100.dp
                ))



    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Iniciar Sesión", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre de usuario") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                loginMessage = if (username == "admin" && password == "1234") {
                    "Inicio de sesión exitoso"
                } else {
                    "Credenciales incorrectas"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors( Color(23, 112, 207))
        ) {
            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(loginMessage)
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    InicioTheme {
        SplashScreen(onContinueClick = {})
    }
}

@Composable
fun LoginScreenPreview() {
    InicioTheme {
        LoginScreen()
    }
}