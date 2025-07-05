# Android Demo App - Flow vs LiveData Collector Behavior

This demo application demonstrates the key differences between Flow and LiveData in Android, specifically focusing on **how different observable types behave when collectors are present or absent**.

## Demo Overview

This demo is based on the Medium article: [Tricky Android Interview Questions: ViewModel & State Handling Edition](https://levelup.gitconnected.com/tricky-android-interview-questions-viewmodel-state-handling-edition-a2f7a99c705f)

### Key Concepts Demonstrated

1. **LiveData Behavior**: Single value holder, lifecycle-aware
2. **Cold Flow Behavior**: Only works when collected, stops when collector is cancelled
3. **Hot Flow with Eagerly**: Starts immediately, continues even without collectors
4. **Hot Flow with WhileSubscribed**: Starts when first collector arrives, stops when no collectors
5. **StateFlow**: Hot Flow similar to LiveData

## Features

### 1. LiveData Counter
- Uses traditional `MutableLiveData` and `LiveData`
- Single value holder
- Lifecycle-aware
- Always active (no start/stop needed)

### 2. StateFlow Counter (Hot Flow)
- Uses `MutableStateFlow` - already a hot Flow
- Single value holder
- Similar behavior to LiveData
- Can be started/stopped for demonstration

### 3. Cold Flow
- Uses `flow { }` builder - creates cold Flow
- Only works when collected
- Stops completely when collector is cancelled
- Demonstrates cold Flow behavior

### 4. Hot Flow (Eagerly)
- Uses `flow { }` with `SharingStarted.Eagerly`
- Starts immediately when ViewModel is created
- Continues running even without collectors
- Demonstrates eager sharing strategy

### 5. Hot Flow (WhileSubscribed)
- Uses `flow { }` with `SharingStarted.WhileSubscribed()`
- Starts when first collector arrives
- Stops when no collectors are active
- Demonstrates lazy sharing strategy

## How to Test

1. **Launch the app** - The Flow vs LiveData demo will be the first screen
2. **Start with all collectors stopped** - Observe which flows continue running
3. **Test each flow type**:
   - **Cold Flow**: ❌ Stops completely when no collectors
   - **Hot Flow (Eagerly)**: ✅ Continues running without collectors
   - **Hot Flow (WhileSubscribed)**: ❌ Stops when no collectors
   - **StateFlow**: ✅ Continues internally (hot Flow)
4. **Start/Stop collectors** to see behavior changes
5. **Key insight**: Eagerly vs WhileSubscribed sharing strategies

## Technical Implementation

### ViewModel Structure

```kotlin
class FlowVsLiveDataViewModel : ViewModel() {
    // LiveData approach
    private val _liveDataCounter = MutableLiveData<Int>(0)
    val liveDataCounter: LiveData<Int> = _liveDataCounter
    
    // Cold Flow - only works when collected
    fun getColdFlow() = flow {
        val currentValue = counter.get()
        emit(currentValue)
        delay(100) // Simulate processing
        emit(currentValue + 1)
    }
    
    // Hot Flow with Eagerly - starts immediately
    private val hotFlowEagerly = flow {
        var value = 0
        while (true) {
            emit(value)
            value++
            delay(1000) // Update every second
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = 0
    )
    
    // Hot Flow with WhileSubscribed - starts when first collector arrives
    private val hotFlowWhileSubscribed = flow {
        var value = 0
        while (true) {
            emit(value)
            value++
            delay(1000) // Update every second
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = 0
    )
}
```

### Key Differences: Collector Behavior

| Approach | Type | Without Collectors | With Collectors | Start Strategy |
|----------|------|-------------------|-----------------|----------------|
| LiveData | Hot | ✅ Active | ✅ Active | Always |
| StateFlow | Hot | ✅ Active | ✅ Active | Always |
| Cold Flow | Cold | ❌ Inactive | ✅ Active | On Collection |
| Hot Flow (Eagerly) | Hot | ✅ Active | ✅ Active | Immediately |
| Hot Flow (WhileSubscribed) | Hot | ❌ Inactive | ✅ Active | On First Collector |

### Sharing Strategies Explained

#### Eagerly
```kotlin
.stateIn(
    scope = viewModelScope,
    started = SharingStarted.Eagerly,
    initialValue = 0
)
```
- **Behavior**: Starts immediately when ViewModel is created
- **Use case**: When you want the flow to start running right away
- **Resource usage**: Continues consuming resources even without collectors

#### WhileSubscribed
```kotlin
.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(),
    initialValue = 0
)
```
- **Behavior**: Starts when first collector arrives, stops when no collectors
- **Use case**: When you want to save resources when no one is observing
- **Resource usage**: Only consumes resources when collectors are active

### Dependencies Used

- `androidx.lifecycle:lifecycle-viewmodel-ktx` - ViewModel and LiveData
- `androidx.lifecycle:lifecycle-livedata-ktx` - LiveData extensions
- `kotlinx-coroutines-android` - Coroutines and Flow

## Learning Objectives

1. **Understand the difference** between cold and hot Flow
2. **Learn sharing strategies** (Eagerly vs WhileSubscribed)
3. **See practical examples** of when each approach is appropriate
4. **Understand resource management** implications
5. **Learn about collector behavior** and lifecycle
6. **Master the key concept**: When flows start/stop based on collectors

## References

- [Original Medium Article](https://levelup.gitconnected.com/tricky-android-interview-questions-viewmodel-state-handling-edition-a2f7a99c705f)
- [Android Developer Documentation - LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [Kotlin Flow Documentation](https://kotlinlang.org/docs/flow.html)
- [Android Developer Documentation - StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
- [Kotlin Coroutines Flow - stateIn](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/state-in.html)

## Running the Demo

1. Clone the repository
2. Open in Android Studio
3. Build and run on a device or emulator
4. Navigate through the demo to see the different behaviors
5. **Key test**: Start/Stop collectors to observe behavior differences

## Branch Information

This demo is implemented in the `demo-flow-vs-livedata` branch.

## 🚀 Quick Start

### Prerequisites
- Android Studio or Cursor IDE
- Git
- Basic knowledge of Android development

### How to Use

1. **Clone this repository** (if you haven't already):
   ```bash
   git clone https://github.com/onuralpavci/Base-Demo-App.git
   cd Base-Demo-App
   ```

2. **Create a new branch for your demo**:
   ```bash
   git checkout -b demo-<topic-name>
   ```
   
   **Example**:
   ```bash
   git checkout -b demo-room-database
   ```

3. **Start implementing your demo** based on the Medium article you want to follow.

4. **Use the .cursorrules file** in this project to guide your implementation.

## 📁 Project Structure

```
Base-Demo-App/
├── app/
│   ├── src/main/
│   │   ├── java/com/avciapps/basedemoapp/
│   │   │   ├── MainActivity.kt
│   │   │   ├── FirstFragment.kt
│   │   │   └── SecondFragment.kt
│   │   └── res/
│   │       ├── layout/
│   │       ├── navigation/
│   │       └── values/
│   └── build.gradle.kts
├── gradle/
│   └── libs.versions.toml
├── .cursorrules
└── README.md
```

## 🎯 Workflow

1. **Read a Medium article** about an Android development concept
2. **Create a new branch** for your demo implementation
3. **Implement the concept** following the article
4. **Add proper documentation** and comments
5. **Test your demo** to ensure it works correctly

## 📚 Example Demos

You can create demos for various Android concepts:

- **Room Database**: CRUD operations, DAO, entities
- **MVVM Pattern**: ViewModels, LiveData, DataBinding
- **Coroutines**: Async operations, background tasks
- **Navigation Component**: Fragment navigation, deep linking
- **Retrofit**: API calls, JSON parsing
- **Dependency Injection**: Hilt, Dagger
- **Jetpack Compose**: Modern UI development

## 🔧 Dependencies

This project uses modern Android development practices:

- **Gradle Version Catalogs**: `libs.versions.toml` for dependency management
- **ViewBinding**: For view access
- **Navigation Component**: For fragment navigation
- **Material Design**: For UI components

## 📝 Adding Dependencies

When adding new dependencies, use the `libs.versions.toml` file:

```toml
# Add to [versions] section
room = "2.6.1"

# Add to [libraries] section
androidx-room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "room" }
```

Then in `app/build.gradle.kts`:
```kotlin
dependencies {
    implementation(libs.androidx.room.runtime)
}
```

## 🤝 Contributing

1. Create a new branch for your demo
2. Implement the Android concept
3. Add proper documentation
4. Test your implementation
5. Commit your changes

## 📖 Resources

- [Android Developer Documentation](https://developer.android.com/)
- [Medium Android Articles](https://medium.com/tag/android)
- [Kotlin Documentation](https://kotlinlang.org/docs/)

## 📄 License

This project is open source and available under the [MIT License](LICENSE).