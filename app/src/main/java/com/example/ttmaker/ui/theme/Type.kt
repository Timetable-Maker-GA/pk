package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily

import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font
import com.example.ttmaker.ManropeFontFamily
import com.ntech.ttmaker.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontName = GoogleFont("Manrope")
val manrope = FontFamily(
    Font(
        googleFont = fontName,
        fontProvider = provider,
    )
)

val ibmPlexSans = FontFamily(
    Font(
        googleFont = GoogleFont("IBM Plex Sans"),
        fontProvider = provider,
    )
)

// Default Material 3 typography values
val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = ManropeFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = ManropeFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = ManropeFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = ManropeFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = ManropeFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = ManropeFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = ManropeFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = ManropeFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = ManropeFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = ManropeFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = ManropeFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = ManropeFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = ManropeFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = ManropeFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = ManropeFontFamily),
)

