# OmniRecipe

**IPORTANT**: This project is currently in development and not yet ready for use.

OmniRecipe is a recipe app that is currently still quite simple and allows to browse through
recipes.
Adding and editing recipes will follow in the future as well as more features.

The app is built with modern Android technologies like Jetpack Compose, Hilt and it is modularized.

The backend for this version of the app is implemented in a different
repository: [OmniRecipe-Backend](https://github.com/Omerixe/OmniRecipe-Backend).
It is currently built to only connect to an API defined in the repository. All details like URLs and
API keys are stored in a
`secrets.properties` file which is not committed to the repository as I don't want to share my data.

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
2. Create a `secrets.properties` file in the root directory of the project
3. Add the following properties to the file:

```
API_URL="your_api_url"
API_KEY="your_api_key"
```

4. Build and run the app
5. Enjoy browsing through recipes

## License

This project is licensed under the terms of the MIT license."