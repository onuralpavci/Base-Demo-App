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