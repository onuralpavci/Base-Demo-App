# Base Demo App

A clean, minimal Android application template designed for demonstrating Android implementations and features. This project is optimized for use with **Cursor IDE** and follows modern Android development practices.

## Overview

This is a simplified Android project that serves as a base template for creating demo applications. It provides a clean starting point with:

- **Modern Architecture**: Uses ViewBinding and Navigation Component
- **Clean UI**: Minimal interface with just essential elements
- **Material Design**: Follows Material Design guidelines
- **Modern Gradle**: Uses `libs.versions.toml` for dependency management
- **Cursor IDE Optimized**: Configured for optimal development experience with Cursor

## Project Structure

```
app/
├── src/main/
│   ├── java/com/avciapps/basedemoapp/
│   │   ├── MainActivity.kt          # Main activity with navigation setup
│   │   └── [Demo Fragments]         # Demo-specific fragments
│   ├── res/
│   │   ├── layout/
│   │   │   ├── activity_main.xml    # Main activity layout
│   │   │   ├── content_main.xml     # Content layout with NavHostFragment
│   │   │   └── [Demo Layouts]       # Demo-specific layouts
│   │   ├── navigation/
│   │   │   └── nav_graph.xml        # Navigation graph
│   │   └── values/
│   │       ├── strings.xml          # String resources
│   │       ├── colors.xml           # Color definitions
│   │       └── themes.xml           # App themes
│   └── AndroidManifest.xml
├── build.gradle.kts                 # App-level build configuration
└── gradle/
    └── libs.versions.toml           # Modern dependency management
```

## Features

- **Modern Architecture**: Clean, maintainable code structure
- **ViewBinding**: Modern way to access views without findViewById
- **Navigation Component**: Type-safe navigation between destinations
- **Material Design**: Uses Material Design components and theming
- **Modern Gradle**: Version catalog-based dependency management
- **Cursor IDE Optimized**: Enhanced development experience

## Getting Started

### Option 1: Using Cursor IDE (Recommended)

1. **Install Cursor IDE**:
   - Download from [cursor.sh](https://cursor.sh)
   - Install and set up your development environment

2. **Clone and Open**:
   ```bash
   git clone https://github.com/onuralpavci/Base-Demo-App.git
   cd Base-Demo-App
   cursor .
   ```

3. **Install Android Development Tools**:
   - Install Android SDK and tools
   - Configure Android SDK path in Cursor
   - Install Android emulator or connect a device

4. **Build and Run**:
   ```bash
   ./gradlew build
   ./gradlew installDebug
   ```

### Option 2: Using Android Studio

1. Clone this repository
2. Open the project in Android Studio
3. Build and run the application

## .cursorrules Configuration

This project includes a `.cursorrules` file that configures AI assistance for Android development. You can use this configuration to create new demos from Medium articles or other sources.

### How to Create New Demos

1. **Provide an Article**: Share a Medium article or any other source about Android development
2. **AI Analysis**: The AI will analyze the article and understand the key concepts
3. **Automatic Implementation**: The AI will create a complete demo app with:
   - Interactive fragments demonstrating the concepts
   - Modern UI with Material Design
   - Proper navigation and documentation
   - All necessary dependencies

### Supported Sources
- Medium articles about Android development
- Android documentation
- Tutorial blogs
- YouTube tutorials (with written content)
- Any other Android development resources

## Customization

This base app is designed to be easily customizable for demonstrating various Android features:

- **Add new fragments**: Create new fragments and add them to the navigation graph
- **Implement features**: Add your specific Android implementation code
- **Modify UI**: Update layouts and themes as needed
- **Add dependencies**: Include any required libraries in `gradle/libs.versions.toml`

## Usage for Demos

This template is perfect for:
- Demonstrating Android UI patterns
- Showing navigation implementations
- Testing new Android features
- Creating tutorial applications
- Prototyping Android concepts
- Learning Android development with AI assistance

## Android Compose Screenshot Testing Demo

This demo app demonstrates how to implement **Compose Screenshot Testing** in Android applications. Screenshot testing is an effective way to verify how your UI looks to users by automatically comparing screenshots against previously approved reference images.

## 🎯 What This Demo Covers

Based on the [Android Compose Screenshot Testing documentation](https://developer.android.com/studio/preview/compose-screenshot-testing), this demo includes:

- **Setup and Configuration**: How to enable Compose Screenshot Testing in your project
- **Sample Components**: Various UI components with different states and themes
- **Screenshot Tests**: Comprehensive test coverage with multiple preview configurations
- **Multi-Preview Testing**: Using `@PreviewParameter` for testing different data sets
- **Theme Testing**: Both light and dark theme variations
- **HTML Reports**: Automatic generation of visual difference reports

## 🚀 Key Features

### Sample Components
- **GreetingCard**: Simple welcome message component
- **CounterCard**: Interactive counter with buttons
- **ProfileCard**: User profile information display
- **SettingsCard**: Settings with toggle switches

### Screenshot Test Coverage
- Individual component tests
- Multi-preview tests with different data
- Light and dark theme variations
- Composite component tests
- Parameterized tests using `PreviewParameterProvider`

## 📋 Requirements

- Android Gradle Plugin 8.5.0-beta01 or higher
- Kotlin 1.9.20 or higher (recommended: Kotlin 2.0+)
- Compose enabled project
- Compose Compiler version 1.5.4 or higher

## 🛠️ Setup

### 1. Dependencies
The demo uses the following key dependencies:

```kotlin
// Screenshot testing plugin
plugins {
    alias(libs.plugins.screenshot)
}

// Screenshot testing dependencies
screenshotTestImplementation(libs.screenshot.validation.api)
screenshotTestImplementation(libs.androidx.compose.ui.tooling)
```

### 2. Configuration
Enable screenshot testing in your project:

```properties
# gradle.properties
android.experimental.enableScreenshotTest=true
```

```kotlin
// build.gradle.kts
android {
    experimentalProperties["android.experimental.enableScreenshotTest"] = true
    testOptions {
        screenshotTests {
            imageDifferenceThreshold = 0.0001f // 0.01%
        }
    }
}
```

## 🧪 Running Screenshot Tests

### Generate Reference Images
First, generate reference images for your composable previews:

```bash
# Linux/macOS
./gradlew updateDebugScreenshotTest

# Windows
gradlew updateDebugScreenshotTest
```

Reference images will be saved in: `app/src/screenshotTestDebug/reference/`

### Validate Screenshots
After making changes, validate against reference images:

```bash
# Linux/macOS
./gradlew validateDebugScreenshotTest

# Windows
gradlew validateDebugScreenshotTest
```

### View Test Reports
HTML reports are generated at: `app/build/reports/screenshotTest/preview/debug/index.html`

## 📁 Project Structure

```
app/
├── src/
│   ├── main/
│   │   └── java/com/avciapps/basedemoapp/
│   │       ├── ui/
│   │       │   ├── components/
│   │       │   │   └── SampleComponents.kt    # Sample UI components
│   │       │   └── theme/
│   │       │       └── Theme.kt               # Compose theme
│   │       ├── MainActivity.kt                # Main activity with navigation
│   │       └── ComposeDemoActivity.kt         # Compose demo screen
│   └── screenshotTest/
│       └── kotlin/com/avciapps/basedemoapp/
│           └── ComposeScreenshotTests.kt      # Screenshot tests
```

## 🎨 Sample Components

### GreetingCard
A simple card that displays a personalized welcome message.

```kotlin
@Composable
fun GreetingCard(name: String) {
    Card {
        Text("Hello $name!")
    }
}
```

### CounterCard
An interactive counter with increment/decrement functionality.

### ProfileCard
Displays user profile information with edit functionality.

### SettingsCard
Settings interface with toggle switches for various options.

## 🧪 Screenshot Test Examples

### Basic Test
```kotlin
@PreviewTest
@Preview(showBackground = true, name = "Greeting Card - Default")
@Composable
fun GreetingCardPreview() {
    BaseDemoAppTheme {
        GreetingCard(name = "Android")
    }
}
```

### Multi-Preview Test
```kotlin
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
```

### Theme Testing
```kotlin
@PreviewTest
@Preview(showBackground = true, name = "Greeting Card - Dark Theme")
@Composable
fun GreetingCardDarkPreview() {
    BaseDemoAppTheme(darkTheme = true) {
        GreetingCard(name = "Android")
    }
}
```

## 🔧 Configuration Options

### Image Difference Threshold
Control the sensitivity of screenshot comparisons:

```kotlin
android {
    testOptions {
        screenshotTests {
            imageDifferenceThreshold = 0.0001f // 0.01%
        }
    }
}
```

### Preview Parameters
Use `PreviewParameterProvider` to test multiple data sets:

```kotlin
class UserNameProvider : PreviewParameterProvider<String> {
    override val values = sequenceOf("Android", "Developer", "Tester")
}
```

## 📊 Test Reports

When screenshot tests fail, an HTML report is generated showing:
- Side-by-side comparison of expected vs actual
- Highlighted differences
- Detailed analysis of visual changes

## 🎯 Best Practices

1. **Use Descriptive Names**: Give previews meaningful names for better test reports
2. **Test Multiple Themes**: Include both light and dark theme tests
3. **Parameterize Tests**: Use `@PreviewParameter` for comprehensive coverage
4. **Regular Updates**: Update reference images when making intentional UI changes
5. **Review Reports**: Always review HTML reports to understand visual differences

## 🚀 Getting Started

1. **Clone and Build**: Build the project to ensure all dependencies are resolved
2. **Generate References**: Run `./gradlew updateDebugScreenshotTest`
3. **Make Changes**: Modify components in `SampleComponents.kt`
4. **Validate**: Run `./gradlew validateDebugScreenshotTest`
5. **Review**: Check the HTML report for visual differences

## 📚 Additional Resources

- [Android Compose Screenshot Testing Documentation](https://developer.android.com/studio/preview/compose-screenshot-testing)
- [Compose Preview Documentation](https://developer.android.com/jetpack/compose/tooling/preview)
- [Material 3 Design System](https://m3.material.io/)

## 🤝 Contributing

This demo is part of the Android Demo App Creator project. Feel free to:
- Add more sample components
- Improve test coverage
- Enhance documentation
- Report issues or suggest improvements

---

**Note**: Compose Screenshot Testing is currently in alpha phase. Features and APIs may change in future releases.

## Requirements

### Development Environment
- **Cursor IDE** (recommended) or **Android Studio**
- **Android SDK** with API 34
- **Java 17** or later
- **Gradle 8.0** or later

### Android Requirements
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)
- **Kotlin**: 1.8+

## Available Demos

This repository includes demo implementations created using the `.cursorrules` configuration:

### 🌟 Coroutines Demo (`coroutines-demo` branch)
A comprehensive demo with 7 interactive fragments demonstrating coroutine concepts from Medium articles.

### 🚀 Create Your Own Demo
Simply provide a Medium article or any Android development resource, and the AI will create a complete demo app for you!

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-demo`)
3. Commit your changes (`git commit -m 'Add amazing demo'`)
4. Push to the branch (`git push origin feature/amazing-demo`)
5. Open a Pull Request

## License

This project is open source and available under the MIT License.