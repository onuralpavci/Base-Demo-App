package com.avciapps.basedemoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.avciapps.basedemoapp.ui.components.*
import com.avciapps.basedemoapp.ui.theme.BaseDemoAppTheme

/**
 * Compose Demo Activity that showcases all the sample components
 * This activity demonstrates the components that are being tested with screenshot testing
 */
class ComposeDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseDemoAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeDemoScreen()
                }
            }
        }
    }
}

@Composable
fun ComposeDemoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Compose Screenshot Testing Demo",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "This screen demonstrates the components that are being tested with Compose Screenshot Testing. " +
                    "Each component below has corresponding screenshot tests that verify their visual appearance.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Display all components
        GreetingCard(name = "Android Developer")
        
        Spacer(modifier = Modifier.height(16.dp))
        
        CounterCard()
        
        Spacer(modifier = Modifier.height(16.dp))
        
        ProfileCard(
            name = "John Doe",
            email = "john.doe@example.com",
            role = "Android Developer"
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        SettingsCard()
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = "Screenshot Testing Instructions:",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "1. Run './gradlew updateDebugScreenshotTest' to generate reference images\n" +
                    "2. Make changes to components\n" +
                    "3. Run './gradlew validateDebugScreenshotTest' to compare with references\n" +
                    "4. Check the HTML report for visual differences",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
} 