# Base Demo App

A clean, minimal Android application template designed for demonstrating Android implementations and features.

## Overview

This is a simplified Android project that serves as a base template for creating demo applications. It provides a clean starting point with:

- **Navigation**: Two fragments with navigation between them
- **Clean UI**: Minimal interface with just essential elements
- **Modern Architecture**: Uses ViewBinding and Navigation Component
- **Material Design**: Follows Material Design guidelines

## Project Structure

```
app/
├── src/main/
│   ├── java/com/avciapps/basedemoapp/
│   │   ├── MainActivity.kt          # Main activity with navigation setup
│   │   ├── FirstFragment.kt         # First fragment with navigation
│   │   └── SecondFragment.kt        # Second fragment with navigation
│   ├── res/
│   │   ├── layout/
│   │   │   ├── activity_main.xml    # Main activity layout
│   │   │   ├── content_main.xml     # Content layout with NavHostFragment
│   │   │   ├── fragment_first.xml   # First fragment layout
│   │   │   └── fragment_second.xml  # Second fragment layout
│   │   ├── navigation/
│   │   │   └── nav_graph.xml        # Navigation graph
│   │   └── values/
│   │       ├── strings.xml          # String resources
│   │       ├── colors.xml           # Color definitions
│   │       └── themes.xml           # App themes
│   └── AndroidManifest.xml
└── build.gradle.kts                 # App-level build configuration
```

## Features

- **Fragment Navigation**: Two fragments that can navigate to each other
- **Clean Layout**: Simple UI with just a title and navigation buttons
- **ViewBinding**: Modern way to access views without findViewById
- **Navigation Component**: Type-safe navigation between destinations
- **Material Design**: Uses Material Design components and theming

## Getting Started

1. Clone this repository
2. Open the project in Android Studio
3. Build and run the application

## Customization

This base app is designed to be easily customizable for demonstrating various Android features:

- **Add new fragments**: Create new fragments and add them to the navigation graph
- **Implement features**: Add your specific Android implementation code
- **Modify UI**: Update layouts and themes as needed
- **Add dependencies**: Include any required libraries in build.gradle.kts

## Usage for Demos

This template is perfect for:
- Demonstrating Android UI patterns
- Showing navigation implementations
- Testing new Android features
- Creating tutorial applications
- Prototyping Android concepts

## Requirements

- Android Studio Arctic Fox or later
- Minimum SDK: API 24 (Android 7.0)
- Target SDK: API 34 (Android 14)
- Kotlin 1.8+

## License

This project is open source and available under the MIT License.