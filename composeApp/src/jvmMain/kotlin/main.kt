import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ru.ivk1800.App
import java.awt.Dimension

fun main() = application {
    Window(
        title = "Recomposition visualization",
        state = rememberWindowState(width = 1400.dp, height = 900.dp),
        onCloseRequest = ::exitApplication,
    ) {
        window.minimumSize = Dimension(1400, 900)
        App()
    }
}
