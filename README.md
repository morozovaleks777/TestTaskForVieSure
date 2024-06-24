# Test Task for VieSure

This project was created as per the technical assignment provided. It uses Jetpack Compose, Room, Retrofit, and Dagger-Hilt. The project employs a multi-module architecture and data encryption in the database. It also follows MVI, MVVM, and Clean Architecture principles.

## Project Description

The project consists of the following screens:

- **Splash Screen**: After a delay of 800ms, the app navigates to the main screen.
- **Main Screen**: Displays a top app bar and a vertically scrollable list of books. Tapping on a book card navigates to the detail screen.
- **Detail Screen**: Shows detailed information about the book. A back arrow in the top app bar allows navigation back to the main screen.
- **Profile Screen**: Accessible from an icon in the top app bar on the main screen. Displays CV information.

### Features

- **Pull to Refresh**: Refreshes data from the API when available. If offline, data is fetched from the local database.
- **Offline Support**: Data is stored in the local database and synchronized with the server when the internet is available.

### Libraries and Tools Used

- **Jetpack Compose**: For building the UI.
- **Room**: For local database storage.
- **Retrofit**: For API requests.
- **Dagger-Hilt**: For dependency injection.
- **Multi-Module Architecture**: For better organization and scalability.
- **Data Encryption**: Ensures security of data stored in the local database.
- **MVI and MVVM Architecture**: For managing UI state and business logic.

## Technical Details

### Splash Screen
- Delays for 800ms before navigating to the main screen.

### Main Screen
- Displays a list of books in a vertically scrollable list.
- Each book card navigates to a detailed screen upon tapping.

### Detail Screen
- Shows detailed information about the selected book.
- Includes a back arrow in the top app bar to return to the main screen.

### Profile Screen
- Accessible from an icon in the top app bar on the main screen.
- Displays CV information.

## Acceptance Criteria
- Implements UI according to provided design.
- Data encryption in the local database.
- Use of Jetpack Compose for building UI.
- API requests with Retrofit.
- Local data storage with Room.
- Dependency injection with Dagger-Hilt.
- Multi-module project setup.
- Use of MVI and MVVM architecture patterns.

## Next Steps
- Further enhancements and optimizations.
- Additional features as per requirements.

