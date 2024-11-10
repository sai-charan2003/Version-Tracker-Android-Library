# App Version Tracker Android Library
[![](https://jitpack.io/v/sai-charan2003/Version-Tracker-Android-Library.svg)](https://jitpack.io/#sai-charan2003/Version-Tracker-Android-Library)

The **App Version Tracker Android Library** is a Kotlin-based solution that enables Android developers to track the latest version of their app with ease. This library is designed for apps distributed outside the Play Store, allowing you to check for updates based on data managed through the App Version Tracker website.

You might wonder: can developers simply use Retrofit and implement this? While the answer is yes, setting up Retrofit and creating DTOs can be tedious for a simple app. This library simplifies the process, making version tracking quick and easy.

### Quick Start

1. **Login and Setup**: Register on the [App Version Tracker website](https://version-tracker.vercel.app/#/register), log in, and add your app details to your account, including version info and download link.

2. **Include the Library**: Add this library to your Android project.

3. **Get Version Info**: Call the `getLatestAppVersion` function with your API key and app name to retrieve the latest version data you’ve entered on the website.


## Installation

App Version Tracker is published to jitpack, so to add jitpack to your project repository
```kotlin
repositories {
	mavenCentral()
	maven { url 'https://jitpack.io' }
    // ...
}
```
Then add the dependency 
```kotlin
implementation ("com.github.sai-charan2003:Version-Tracker-Android-Library:<latest-version>")
```




## Usage

### Calling the API and Getting the Response

Ensure that you have added the Internet permission in your AndroidManifest.xml 😉

To retrieve the latest app version information, use the following code snippet:

```kotlin
VersionTracker.getLatestAppVersion(<App_Name>, <API_KEY>).observeForever { processState ->
    when (processState) {
        is ProcessState.Error -> {
            // Handle error response
            // e.g., Log.e("AppVersionTracker", "Error: ${processState.message}")
        }
        ProcessState.Loading -> {
            // Handle loading state
            // e.g., show a loading indicator if needed
        }
        is ProcessState.Success -> {
            val response = processState.autoUpdateDTO
            // Use the response data
            // e.g., Log.d("AppVersionTracker", "App version: ${response.appVersion}")
        }
    }
}
```
The response is of type AutoUpdateDTO, which contains the following fields:
```kotlin
data class AutoUpdateDTO(
appName: String? = null,
appVersion: Double? = null,
appVersionCode: Double? = null,
appDownloadLink: String? = null,
appUUID: String? = null
)
```
You can find the backend source code for the App Version Tracker [here](https://github.com/sai-charan2003/App-Version-Tracker-Backend).

The App Version Tracker website is built using Flutter. You can access the frontend source code [here](https://github.com/sai-charan2003/app-version-tracker-frontend).

