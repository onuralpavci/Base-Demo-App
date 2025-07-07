package com.avciapps.basedemoapp

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.android.tools.screenshot.PreviewTest
import com.avciapps.basedemoapp.ui.components.*
import com.avciapps.basedemoapp.ui.theme.BaseDemoAppTheme

/**
 * Preview parameter provider for different user names
 */
class UserNameProvider : PreviewParameterProvider<String> {
    override val values = sequenceOf("Android", "Developer", "Tester")
}

/**
 * Preview parameter provider for different profile data
 */
class ProfileDataProvider : PreviewParameterProvider<Triple<String, String, String>> {
    override val values = sequenceOf(
        Triple("John Doe", "john.doe@example.com", "Developer"),
        Triple("Jane Smith", "jane.smith@example.com", "Designer"),
        Triple("Bob Wilson", "bob.wilson@example.com", "Manager")
    )
}

// ===== GREETING CARD SCREENSHOT TESTS =====

@PreviewTest
@Preview(showBackground = true, name = "Greeting Card - Default")
@Composable
fun GreetingCardPreview() {
    BaseDemoAppTheme {
        GreetingCard(name = "Android")
    }
}

@PreviewTest
@Preview(showBackground = true, name = "Greeting Card - Different Names")
@Composable
fun GreetingCardWithNamesPreview(
    @PreviewParameter(UserNameProvider::class) name: String
) {
    BaseDemoAppTheme {
        GreetingCard(name = name)
    }
}

@PreviewTest
@Preview(showBackground = true, name = "Greeting Card - Dark Theme")
@Composable
fun GreetingCardDarkPreview() {
    BaseDemoAppTheme(darkTheme = true) {
        GreetingCard(name = "Android")
    }
}

// ===== COUNTER CARD SCREENSHOT TESTS =====

@PreviewTest
@Preview(showBackground = true, name = "Counter Card - Initial State")
@Composable
fun CounterCardPreview() {
    BaseDemoAppTheme {
        CounterCard()
    }
}

@PreviewTest
@Preview(showBackground = true, name = "Counter Card - Dark Theme")
@Composable
fun CounterCardDarkPreview() {
    BaseDemoAppTheme(darkTheme = true) {
        CounterCard()
    }
}

// ===== PROFILE CARD SCREENSHOT TESTS =====

@PreviewTest
@Preview(showBackground = true, name = "Profile Card - Default")
@Composable
fun ProfileCardPreview() {
    BaseDemoAppTheme {
        ProfileCard(
            name = "John Doe",
            email = "john.doe@example.com",
            role = "Developer"
        )
    }
}

@PreviewTest
@Preview(showBackground = true, name = "Profile Card - Different Profiles")
@Composable
fun ProfileCardWithDataPreview(
    @PreviewParameter(ProfileDataProvider::class) profileData: Triple<String, String, String>
) {
    BaseDemoAppTheme {
        ProfileCard(
            name = profileData.first,
            email = profileData.second,
            role = profileData.third
        )
    }
}

@PreviewTest
@Preview(showBackground = true, name = "Profile Card - Dark Theme")
@Composable
fun ProfileCardDarkPreview() {
    BaseDemoAppTheme(darkTheme = true) {
        ProfileCard(
            name = "John Doe",
            email = "john.doe@example.com",
            role = "Developer"
        )
    }
}

// ===== SETTINGS CARD SCREENSHOT TESTS =====

@PreviewTest
@Preview(showBackground = true, name = "Settings Card - Default")
@Composable
fun SettingsCardPreview() {
    BaseDemoAppTheme {
        SettingsCard()
    }
}

@PreviewTest
@Preview(showBackground = true, name = "Settings Card - Dark Theme")
@Composable
fun SettingsCardDarkPreview() {
    BaseDemoAppTheme(darkTheme = true) {
        SettingsCard()
    }
}

// ===== COMPOSITE SCREENSHOT TESTS =====

@PreviewTest
@Preview(showBackground = true, name = "All Components - Light Theme")
@Composable
fun AllComponentsPreview() {
    BaseDemoAppTheme {
        Column {
            GreetingCard(name = "Android")
            CounterCard()
            ProfileCard(
                name = "John Doe",
                email = "john.doe@example.com",
                role = "Developer"
            )
            SettingsCard()
        }
    }
}

@PreviewTest
@Preview(showBackground = true, name = "All Components - Dark Theme")
@Composable
fun AllComponentsDarkPreview() {
    BaseDemoAppTheme(darkTheme = true) {
        Column {
            GreetingCard(name = "Android")
            CounterCard()
            ProfileCard(
                name = "John Doe",
                email = "john.doe@example.com",
                role = "Developer"
            )
            SettingsCard()
        }
    }
} 