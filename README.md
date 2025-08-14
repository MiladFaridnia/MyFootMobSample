# Football Player Browser

An Android application that displays football player data for all leagues and players provided.  
Built with Jetpack Compose and following Clean Architecture principles for scalability and maintainability.

## Features

- List of Players by League  
  Browse players from all available leagues with smooth pagination.
- Sorting  
  Sort players by desired criteria (e.g., name, goals) across the entire dataset.
- Follow / Unfollow Players  
  Mark your favorite players and manage them easily.
- Persisted Follow State  
  Followed players are stored locally so your preferences remain after closing the app.
- Followed Players Screen  
  Dedicated screen to quickly access all your followed players.
- Light / Dark Mode  
- Tests
    - Unit tests for core logic.
    - UI tests for critical user flows.

## Screenshots

| Main Player List | Sorting | Followed Players |
| ---------------- | ------- | ---------------- |
| ![Main Screen](C:\Users\Asus\AndroidStudioProjects\MyFootMobSample\Screenshot_light_theme.png) | ![Sorting Screen](C:\Users\Asus\AndroidStudioProjects\MyFootMobSample\Screenshot_dark_theme.png) | ![Followed Screen](C:\Users\Asus\AndroidStudioProjects\MyFootMobSample\Screenshot_sort.png) |

## Architecture

The app follows Clean Architecture with three main layers:

1. **Presentation Layer**
    - Jetpack Compose UI
    - ViewModels (MVVM pattern)
    - State management with Kotlin Flow / StateFlow

2. **Domain Layer**
    - Use cases encapsulating business logic
    - Domain models independent of frameworks

3. **Data Layer**
    - Repository pattern
    - Local data storage (Room or DataStore)
    - Remote data source (Retrofit)

## Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: Clean Architecture + MVVM
- **Dependency Injection**: Hilt
- **Networking**: Retrofit + OkHttp
- **Pagination**: Paging 3
- **Local Storage**: Room or DataStore
- **Coroutines**: Kotlin Coroutines + Flow
- **Testing**: JUnit, MockK, Espresso / Compose UI Tests

## Getting Started

### Prerequisites
- Android Studio Giraffe or newer
- JDK 17+
- Internet connection for fetching player data

### Installation
1. Clone the repository:
   ```bash
   https://github.com/MiladFaridnia/MyFootMobSample.git