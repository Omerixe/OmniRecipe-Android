# OmniRecipe

**IMPORTANT**: This project is currently in development and not yet ready for use.

OmniRecipe is a recipe app that is currently still quite simple and allows to browse through
recipes.
Adding and editing recipes will follow in the future as well as more features.

The app is built with modern Android technologies like Jetpack Compose, Hilt and it is modularized.

The app uses a mock backend by default but can be connected to a network backend.

The api/backend for this version of the app is implemented in a different
repository: [OmniRecipe-Backend](https://github.com/Omerixe/OmniRecipe-Backend).

## Features

- Browse through recipes

**Outlook:**

- Delete recipes
- Add recipes
- Edit recipes

## Tech Stack

- [Kotlin](https://kotlinlang.org/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Hilt](https://dagger.dev/hilt/)
- [Ktor](https://ktor.io/)
- [Modularization](https://developer.android.com/topic/modularization)

## Architecture

The app is built with clean architecture in mind but does not follow it to every last detail.
Although the app is still small, I have opted for modularization to get a feeling for the overhead
it can create but also to see if the advantages make it worth.
It is modularized in a way which is based on the approach
in [NowInAndroid](https://github.com/android/nowinandroid/tree/main).

## Setup

I will soon provide a way to run the app without the need to set up the backend yourself by using a
mock backend.

1. Clone the repository
2. Build and run the app
3. Enjoy browsing through recipes

If you want to run the app with the real server, you need to set up the backend as well.
Before you run the app, you need to set it up to use the real server.

1. In the `build.gradle.kts` file of the `app` module, change the `useMockBackend` variable
   to `false`.
2. Create a `secrets.properties` file in the root directory of the project
3. Add the following properties to the file:

```
API_URL="your_api_url"
API_KEY="your_api_key"
```

## License

This project is licensed under the terms of the MIT license."
