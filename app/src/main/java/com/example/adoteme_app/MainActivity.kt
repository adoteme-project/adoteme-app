import android.os.Bundle
import android.widget.GridLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.adoteme_app.ui.components.GridLayout.Animal
import com.example.adoteme_app.ui.components.GridLayout.GridLayout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {}
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_2, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MyApp {
        val animals = listOf(
            Animal(
                id = 1,
                name = "Rex",
                idade = 3,
                sexo = "Macho",
                imageUrl = ""
            ),
            Animal(
                id = 2,
                name = "Luna",
                idade = 2,
                sexo = "Fêmea",
                imageUrl = "https://www.petz.com.br/blog/wp-content/uploads/2019/03/cachorro-mini-pet-1280x720.jpg"
            ),
            // Adicione mais animais aqui...
        )
        GridLayout(
            animals = animals,
            columns = 2,
        )
    }
}