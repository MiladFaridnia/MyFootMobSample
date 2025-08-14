package ir.miare.androidcodechallenge.presentation.util

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ir.miare.androidcodechallenge.R

private val LightColors = lightColorScheme(
    primary = Color(0xFF0ABAB5),          // vibrant teal cyan
    onPrimary = Color.White,
    tertiary = Color(0xFFADEED9), // soft mint background for primary
    onPrimaryContainer = Color(0xFF00332F),

    primaryContainer = Color(0xFF56DFCF),        // aqua accent
    onSecondary = Color(0xFF003732),
    secondaryContainer = Color(0xFFD6FFF8), // very pale aqua
    onSecondaryContainer = Color(0xFF003230),

    background = Color(0xFFF6FFFD),       // subtle mint-tinted white
    onBackground = Color(0xFF1A1C1C),

    surface = Color(0xFFFAFFFD),           // clean white surface with mint hue
    onSurface = Color(0xFF202121),

    error = Color(0xFFB3261E),
    onError = Color.White,

    surfaceVariant = Color(0xFFE0F5F1),   // pastel aqua-gray
    onSurfaceVariant = Color(0xFF3E4E4B),

    surfaceTint = Color(0xFF0ABAB5),

    secondary = Color(0xFFFFEDF3),         // soft blush pink for warm contrast
    onTertiary = Color(0xFF3E1E25),
)

/*private val LightColors = lightColorScheme(
    primary = Color(0xFF3E5F44),          // main brand green
    onPrimary = Color.White,
    primaryContainer = Color(0xFF5E936C), // lighter green container
    onPrimaryContainer = Color(0xFF0E2A1A),

    secondary = Color(0xFF5E936C),        // softer accent green
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE8FFD7), // very light pastel green
    onSecondaryContainer = Color(0xFF1F331E),

    background = Color(0xFFF6FFF3),       // almost white with a hint of green
    onBackground = Color(0xFF1A1A1A),

    surface = Color(0xFFF2F7F0),          // soft surface tone
    onSurface = Color(0xFF2E2E2E),

    error = Color(0xFFB3261E),
    onError = Color.White,

    surfaceVariant = Color(0xFFD8E6D6),   // muted green-gray
    onSurfaceVariant = Color(0xFF394B3C),

    surfaceTint = Color(0xFF5E936C),

    tertiary = Color(0xFF93DA97),         // matching tertiary with primaryContainer
    onTertiary = Color(0xFF11391F),
)*/

private val DarkColors = darkColorScheme(
    primary = Color(0xFF9EC8B9),          // mint green for visibility in dark
    onPrimary = Color(0xFF0A1E18),
    primaryContainer = Color(0xFF1B4242), // deep teal
    onPrimaryContainer = Color(0xFFBFE6D6),

    secondary = Color(0xFF5C8374),        // soft teal-green
    onSecondary = Color(0xFF0D1B15),
    secondaryContainer = Color(0xFF092635),
    onSecondaryContainer = Color(0xFFB6D7CA),

    background = Color(0xFF0B1A1A),       // almost black teal
    onBackground = Color(0xFFE0F2EA),

    surface = Color(0xFF102C2C),          // slightly lighter than background
    onSurface = Color(0xFFDDEAE3),

    error = Color(0xFFE57373),
    onError = Color(0xFF370000),

    surfaceVariant = Color(0xFF294444),   // muted deep teal
    onSurfaceVariant = Color(0xFFC8E0D9),

    surfaceTint = Color(0xFF5C8374),

    tertiary = Color(0xFF4A9782),
//    tertiary = Color(0xFF113F67),
    onTertiary = Color(0xFF0E231E),
)

private val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.dm_sans_variable_font)),
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold,
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.dm_sans_variable_font)),
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium,
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.dm_sans_variable_font)),
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.dm_sans_variable_font)),
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.dm_sans_variable_font)),
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.dm_sans_variable_font)),
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.dm_sans_variable_font)),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.dm_sans_variable_font)),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.dm_sans_variable_font)),
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
    )
)

@Composable
fun MyFootMobTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )

}