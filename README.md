# TUS-MyBuddy-Mobile-App-Development

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
