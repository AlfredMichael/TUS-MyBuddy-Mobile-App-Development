# TUS-MyBuddy-Mobile-App-Development

## About the App

TUS-MyBuddy is a social network for students at the Technological University Of Shannon. It allows people with similar interests and hobbies to find each other and send buddy requests. Once a request is accepted, users can chat with each other. The app is built using Firebase architecture.

Please note that this app is meant for test purposes only. Messages are stored in a real-time database and are not encrypted. The app is intended for students who attend TUS.

## Test the App

To test the app, download the `app-debug.apk` file from the `release` folder in this GitHub repository and transfer it to your android device. Use the following test credentials to log in:

- Email: K00987789@student.tus.ie, Password: @sharonmackie20
- Email: K00012211@student.tus.ie, Password: @Neilmack12
- Email: K00345543@student.tus.ie, Password: @michaelalfred19

## Firebase Services

This project uses several Firebase services:

- **Firebase Authentication**: Handles the registration and login of users.
- **Firebase Realtime Database**: Stores user profiles and messages.
- **Firebase Storage**: Stores user profile pictures.

A `.json` file (`tus-mybuddy-default-rtdb-export.json`) is included in this repository, displaying the structure of the realtime database. This file can guide anyone who wants to create a similar project.

## Important Note

The `google-services.json` file has been removed from this repository for security reasons. This file contains sensitive Firebase credentials. If you want to run this project, you will need to supply your own `google-services.json` file. You can generate your own `google-services.json` file by creating a new Firebase project.

## Permissions

If you want to use this project, please get permission from me first. As long as you're not using it for commercial purposes, I'd be happy to assist!

For any queries, feel free to reach out to me at k00290602@student.tus.ie.


-----------------------------------  ----------------------------------------------------------------------- --------------------------------
## Week :one:
- FEATURES THAT HAVE BEEN COMPLETED (:heavy_check_mark:)
  - Home Screen UI
  - Login Screen UI
  - Sign Up Screen UI
  - Profile Screen UI
  - Profile Picture Upload With Coil

- WORK IN PROGRESS (:heavy_multiplication_x:)
  - Registration and login with firebase authentication
  - Getting dummy student data and converting it to JSON (For email validation - on register)
  - Implementing the skills, hobbies and interests dropdown 

## Week :two:
- FEATURES THAT HAVE BEEN COMPLETED (:heavy_check_mark:)
  - Navigation with NavGraph
  - Firebase Setup
  - Data Classes - Users and Students
  - RegistrationViewModel - Logic: checks if the entered email exists in the student data in the Firebase Realtime Database. If the email exists, it registers the user with Firebase     
    Authentication and updates the UsersData in the Firebase Realtime Database with the user's and student's information.
  - Error Handling - If an error occurs during the registration process, it posts the error message to a LiveData called **errorMessage**.
  - Created a ProfileViewModel that fetches user data from Firebase based on a user ID and exposes it through a LiveData object
  - Modified the ProfileScreen
  - Modified the ProfileScreenMainContent
  - Used coroutines to perform an asynchronous network request in a structured and non-blocking way, and then posted the result to a LiveData to be displayed in the UI.
  - Completed Part of the profile screen
  - Validation (Fullname, Password, Email)


- WORK IN PROGRESS (:heavy_multiplication_x:)
  - Allow users to create Profile
  - Login
  - Upload Profile Images
  - Implementing the skills, hobbies and interests dropdown (:Or letting users enter hobbies, skills and interests as a delimited string)

## Week :three:
- FEATURES THAT HAVE BEEN COMPLETED (:heavy_check_mark:)
  - Image Upload to Firebase Storage
  - Data Update in Firebase Realtime Database
  - Firebase Authentication Integration
  - Input Validation and Error Handling
  - LiveData Usage
  - Jetpack Compose UI Updates


- WORK IN PROGRESS (:heavy_multiplication_x:)
  - Home Screen
  - Main Functionality
 
## Week :four:
- FEATURES THAT HAVE BEEN COMPLETED (:heavy_check_mark:)
  - Chat Functionality (Based on buddies)
  - Chat Menu
  - Block User
  - Execute functions with couroutines
  - Notifications
  - Connect with buddies
  - LogOut
  - Call Service
  - Profile Page
  - Other User Profile Page
  - Internet Connection Error Message
  - TUS Theme
