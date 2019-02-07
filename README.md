# Hooked

for architecture: -Implemented the clean architecture designed by Uncle Bob: 4 layers: domain, uses-cases, data-access and presentation) the app module is the main module, which is responsible for managing dependencies and injection.

-I used Kotlin as a main langage for developing this app.

I used the gradle buildSrc feature to support an easy centralized dependency management for all modules.

-I Used the latest APIs from Jetpack and Square which are recommended by Google.

-I choosed Android 4.0 / API 14 as min-SDK, because it can support 100% of devices even Data Binding Library works on it, and api 28 as target SDK.

for edge cases : I cut text when description is too long more than 4 lines. I display a placeholder and an error screen for the first screen depending on the state of the request (LOADING, ERROR or SUCCESS)

for performance : I used a lazy network request, so that I check if any existant local data and if it needs an update before letting the app request the remote data. Second screen receives all the messages data, with one time shot, requesting it from the local database. It will just show the messages that have a cardinal-ID less than the total count of the next button click.

cheers!
