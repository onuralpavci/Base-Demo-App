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

## Cursor IDE Features

This project is optimized for Cursor IDE development:

### 🤖 AI-Powered Development
- **Smart Code Completion**: Enhanced Kotlin and Android code suggestions
- **Context-Aware Assistance**: AI understands Android development patterns
- **Automated Refactoring**: Intelligent code restructuring and optimization
- **Bug Detection**: Proactive identification of potential issues

### 📁 Project Organization
- **Intelligent File Navigation**: Quick access to related files
- **Smart Search**: Find code, resources, and dependencies efficiently
- **Git Integration**: Seamless version control with AI-powered commit messages
- **Terminal Integration**: Built-in terminal for Gradle commands

### 🛠️ Android Development Tools
- **Gradle Integration**: Direct build and sync capabilities
- **Layout Preview**: Visual XML layout editing
- **Resource Management**: Easy access to strings, colors, and themes
- **Navigation Graph Editor**: Visual navigation setup

## Demo Development Workflow

### 1. Branch Management
```bash
# Always create a new branch for demos
git checkout main
git checkout -b [demo-name]-demo
```

### 2. Development Process
1. **Analyze Requirements**: Understand the Android concept to demonstrate
2. **Plan Implementation**: Design the demo structure and navigation
3. **Add Dependencies**: Update `gradle/libs.versions.toml` with required libraries
4. **Implement Features**: Create fragments, layouts, and business logic
5. **Test and Refine**: Build, test, and optimize the demo
6. **Document**: Update README and add inline comments

### 3. Code Quality
- Follow Android best practices and Material Design guidelines
- Use modern Android development patterns (ViewBinding, Navigation Component)
- Write clean, readable, and well-commented code
- Include proper error handling where applicable

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

## .cursorrules Configuration

This project includes a `.cursorrules` file that configures AI assistance for Android development:

- **Branch Management**: Automatic branch creation for demo development
- **Dependency Management**: Smart suggestions for `libs.versions.toml`
- **Code Patterns**: Android best practices and modern patterns
- **Documentation**: Automated README and comment generation

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

This repository includes several demo implementations:

### 🌟 Coroutines Demo (`coroutines-demo` branch)
- **7 Interactive Fragments**: Comprehensive coroutine concepts
- **Scope Management**: Lifecycle-aware coroutine scopes
- **Job Control**: Cancellation and supervision patterns
- **Context Combinations**: Thread dispatchers and context merging
- **Structured Concurrency**: Parent-child coroutine relationships

### 🚀 Creating New Demos
To create a new demo:
1. Follow the branch management workflow
2. Use the `.cursorrules` configuration
3. Implement your Android concept
4. Document and test thoroughly
5. Push to a new branch

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-demo`)
3. Commit your changes (`git commit -m 'Add amazing demo'`)
4. Push to the branch (`git push origin feature/amazing-demo`)
5. Open a Pull Request

## License

This project is open source and available under the MIT License.