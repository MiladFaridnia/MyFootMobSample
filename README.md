# Football Player Browser

An Android application that displays football player data for all leagues and players provided.  
Built with Jetpack Compose and following Clean Architecture principles for scalability and maintainability.

## Features

- List of Players by League  
  Browse players from all available leagues with smooth pagination.
- Followed Players Screen  
  Dedicated screen to quickly access all your followed players.
- Sorting  
  Sort players by desired criteria (e.g., name, goals) across the entire dataset.
- Follow / Unfollow Players  
  Mark your favorite players and manage them easily.
- Persisted Follow State  
  Followed players are stored locally so your preferences remain after closing the app.
- Light / Dark Mode  
- Tests
    - Unit tests for core logic.
    - UI tests for critical user flows.

## Screenshots

| Dark Theme | Light Theme | Sorting |
| ---------------- | ------- | ---------------- |
| ![Dark Theme](https://github.com/MiladFaridnia/MyFootMobSample/blob/main/Screenshot_dark_theme.png) | ![Light Theme](https://github.com/MiladFaridnia/MyFootMobSample/blob/main/Screenshot_light_theme.png) | ![Sorting](https://github.com/MiladFaridnia/MyFootMobSample/blob/main/Screenshot_sort.png) |

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
    - Local data storage (Room)
    - Remote data source (Retrofit)

## Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: Clean Architecture + MVVM
- **Dependency Injection**: Hilt
- **Networking**: Retrofit + OkHttp
- **Pagination**: Paging 3
- **Local Storage**: Room
- **Coroutines**: Kotlin Coroutines + Flow
- **Testing**: JUnit, MockK, Espresso

## Getting Started

### Prerequisites
- Android Studio Giraffe or newer
- JDK 17+
- Internet connection for fetching player data

### Installation
1. Clone the repository:
   ```bash
   https://github.com/MiladFaridnia/MyFootMobSample.git
