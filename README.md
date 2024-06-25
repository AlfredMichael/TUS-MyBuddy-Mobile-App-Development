# TUS-MyBuddy-Mobile-App-Development

## Test App (Info)
  - Please open the TUSMyBuddy folder in android studio for the implementations to load properly
  - Registration is Async, so there is a delay of about 5 to 6 seconds for the profile page (After Registration)
  - Test Emails {k00119023@student.tus.ie, k00780945@student.tus.ie, k00654321@student.tus.ie}
  - The test emails are for registration, any full name and password can be added...Please wait for 5 to 6 seconds after clicking the registration button (Reason: When the button is clicked it adds the data to the authentication, it then goes to the student data to make sure that the email exists then gets the data and inserts it into a different data base called the student data... So it runs multiple functions at the same time)

## Important Note

The `google-services.json` file has been removed from this repository for security reasons. This file contains sensitive Firebase credentials. If you want to run this project, you will need to supply your own `google-services.json` file.

You can generate your own `google-services.json` file by creating a new Firebase project. Here's how you can do it:

1. Go to the Firebase console.
2. Click on 'Add project' and follow the on-screen instructions to create a new project.
3. Once your project is created, click on 'Add Firebase to your Android app'.
4. Follow the on-screen instructions to register your app and download the `google-services.json` file.
5. Place the `google-services.json` file in the `app/` directory of your local clone of this repository.

## Firebase Services

This project uses several Firebase services:

- **Firebase Authentication**: This service is used for handling the registration and login of users.
- **Firebase Realtime Database**: This service is used for storing user profiles and messages.
- **Firebase Storage**: This service is used for storing user profile pictures.

A `.json` file (`tus-mybuddy-default-rtdb-export.json`) has been added to this repository, displaying the structure of the realtime database. This file can guide anyone who wants to create a similar project.

## Permissions

If you want to use this project, please get permission from me first. As long as you're not using it for commercial purposes, I'd be happy to assist!

For any queries, feel free to reach out to me at k00290602@student.tus.ie 



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
