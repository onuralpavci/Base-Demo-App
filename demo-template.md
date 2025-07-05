# Android Demo App Template

## Demo App Structure

### 1. Project Setup
```bash
# Clone the base demo app
git clone https://github.com/onuralpavci/Base-Demo-App.git Android-[Feature]-Demo
cd Android-[Feature]-Demo

# Update project name and package
# Update app/build.gradle.kts
# Update AndroidManifest.xml
# Update package names in Kotlin files
```

### 2. Dependencies to Add (libs.versions.toml)
The base app uses modern Gradle dependency management with `libs.versions.toml`.

**Step 1: Add versions to `gradle/libs.versions.toml`**
```toml
[versions]
# Existing versions...
room = "2.6.1"
retrofit = "2.9.0"
coroutines = "1.7.3"
hilt = "2.48"
lifecycle = "2.7.0"

[libraries]
# Existing libraries...
androidx-room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "room" }
androidx-room-ktx = { group = "androidx.room", name = "room-ktx", version.ref = "room" }
androidx-room-compiler = { group = "androidx.room", name = "room-compiler", version.ref = "room" }
retrofit-core = { group = "com.squareup.retrofit2", name = "retrofit", version.ref = "retrofit" }
retrofit-converter-gson = { group = "com.squareup.retrofit2", name = "converter-gson", version.ref = "retrofit" }
kotlinx-coroutines-android = { group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-android", version.ref = "coroutines" }
androidx-lifecycle-viewmodel-ktx = { group = "androidx.lifecycle", name = "lifecycle-viewmodel-ktx", version.ref = "lifecycle" }
androidx-lifecycle-livedata-ktx = { group = "androidx.lifecycle", name = "lifecycle-livedata-ktx", version.ref = "lifecycle" }
```

**Step 2: Add dependencies to `app/build.gradle.kts`**
```kotlin
dependencies {
    // Existing dependencies...
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler) // For annotation processing
    
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.kotlinx.coroutines.android)
    
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
}
```

### 3. Fragment Structure
```kotlin
// Create new fragments for different demo aspects
class FeatureDemoFragment : Fragment() {
    private var _binding: FragmentFeatureDemoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeatureDemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Implement feature demonstration here
        setupFeatureDemo()
    }

    private fun setupFeatureDemo() {
        // Add your feature implementation
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
```

### 4. Layout Structure
```xml
<!-- fragment_feature_demo.xml -->
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <TextView
        android:id="@+id/title_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/feature_demo_title"
        android:textSize="24sp"
        android:textStyle="bold"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <!-- Add your feature-specific UI components here -->

</androidx.constraintlayout.widget.ConstraintLayout>
```

### 5. Navigation Setup
```xml
<!-- Add to nav_graph.xml -->
<fragment
    android:id="@+id/featureDemoFragment"
    android:name="com.avciapps.basedemoapp.FeatureDemoFragment"
    android:label="@string/feature_demo_label"
    tools:layout="@layout/fragment_feature_demo" />

<action
    android:id="@+id/action_FirstFragment_to_featureDemoFragment"
    app:destination="@id/featureDemoFragment" />
```

### 6. Strings and Resources
```xml
<!-- Add to strings.xml -->
<string name="feature_demo_title">Feature Demo</string>
<string name="feature_demo_label">Feature Demo</string>
```

### 7. README Template
```markdown
# Android [Feature] Demo

## Overview
This demo app demonstrates [specific Android feature/concept] based on the article: [Article Title]

## Key Concepts Covered
- [Concept 1]
- [Concept 2]
- [Concept 3]

## Features Demonstrated
- [Feature 1]: Brief description
- [Feature 2]: Brief description
- [Feature 3]: Brief description

## How to Run
1. Clone this repository
2. Open in Android Studio
3. Build and run the application
4. Navigate through the different screens to see the feature in action

## Implementation Details
[Brief explanation of how the feature is implemented]

## References
- Original Article: [Link to Medium article]
- Documentation: [Link to official Android documentation]

## Screenshots
[Add screenshots of the demo in action]
```

## Common Demo Types

### 1. Database Demos (Room)
**Dependencies needed:**
```toml
[versions]
room = "2.6.1"

[libraries]
androidx-room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "room" }
androidx-room-ktx = { group = "androidx.room", name = "room-ktx", version.ref = "room" }
androidx-room-compiler = { group = "androidx.room", name = "room-compiler", version.ref = "room" }
```

**Implementation examples:**
- Entity creation
- DAO implementation
- CRUD operations
- Database relationships
- Migration examples

### 2. Architecture Demos (MVVM, MVI, etc.)
**Dependencies needed:**
```toml
[versions]
lifecycle = "2.7.0"
coroutines = "1.7.3"

[libraries]
androidx-lifecycle-viewmodel-ktx = { group = "androidx.lifecycle", name = "lifecycle-viewmodel-ktx", version.ref = "lifecycle" }
androidx-lifecycle-livedata-ktx = { group = "androidx.lifecycle", name = "lifecycle-livedata-ktx", version.ref = "lifecycle" }
kotlinx-coroutines-android = { group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-android", version.ref = "coroutines" }
```

**Implementation examples:**
- ViewModel implementation
- LiveData usage
- Repository pattern
- Dependency injection
- State management

### 3. UI/UX Demos
**Dependencies needed:**
```toml
[versions]
recyclerview = "1.3.2"
cardview = "1.0.0"

[libraries]
androidx-recyclerview = { group = "androidx.recyclerview", name = "recyclerview", version.ref = "recyclerview" }
androidx-cardview = { group = "androidx.cardview", name = "cardview", version.ref = "cardview" }
```

**Implementation examples:**
- Custom views
- Animations
- Material Design components
- RecyclerView implementations
- ConstraintLayout examples

### 4. Network Demos
**Dependencies needed:**
```toml
[versions]
retrofit = "2.9.0"
okhttp = "4.12.0"

[libraries]
retrofit-core = { group = "com.squareup.retrofit2", name = "retrofit", version.ref = "retrofit" }
retrofit-converter-gson = { group = "com.squareup.retrofit2", name = "converter-gson", version.ref = "retrofit" }
okhttp-logging = { group = "com.squareup.okhttp3", name = "logging-interceptor", version.ref = "okhttp" }
```

**Implementation examples:**
- Retrofit implementation
- API calls
- JSON parsing
- Error handling
- Caching strategies

### 5. Permission Demos
**Dependencies needed:**
```toml
[versions]
activity = "1.8.2"

[libraries]
androidx-activity-ktx = { group = "androidx.activity", name = "activity-ktx", version.ref = "activity" }
```

**Implementation examples:**
- Runtime permissions
- Permission handling
- Camera/Gallery access
- Location services
- Storage access

### 6. Work Manager Demos
**Dependencies needed:**
```toml
[versions]
work = "2.9.0"

[libraries]
androidx-work-runtime-ktx = { group = "androidx.work", name = "work-runtime-ktx", version.ref = "work" }
```

**Implementation examples:**
- Background tasks
- Periodic work
- Work constraints
- Work chaining
- Data passing

## Best Practices

1. **Keep it focused**: Each demo should focus on one main concept
2. **Make it interactive**: Allow users to interact with the feature
3. **Show progression**: Demonstrate different aspects or levels of the feature
4. **Include comments**: Explain the implementation with inline comments
5. **Handle errors**: Show proper error handling where applicable
6. **Follow conventions**: Use Android development best practices
7. **Test thoroughly**: Ensure the demo works correctly
8. **Use modern Gradle**: Always use `libs.versions.toml` for dependency management 