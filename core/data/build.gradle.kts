import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.serialization)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.ksp)
}

// Change this property if you want to switch to the real server implementation
// Can also be changed by setting `USE_MOCK` environment variable to true or false

// Make sure your secrets.properties file is in the root project directory and contains the following key/value pairs:
// API_KEY=yourApiKey
// API_URL=https://yourApiUrl.com
// Can also be changed by setting `API_KEY` and `API_URL` enviroment variables
val apikeyPropertiesFile = rootProject.file("secrets.properties")
val apikeyProperties = Properties()
apikeyProperties.load(FileInputStream(apikeyPropertiesFile))


android {
    namespace = "ch.omerixe.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        // should correspond to key/value pairs inside the file
        buildConfigField(
            "String",
            "API_KEY",
            System.getenv("API_KEY") ?: apikeyProperties.getProperty("API_KEY", "defaultApiKey")
        )
        buildConfigField(
            "String",
            "API_URL",
            System.getenv("API_URL") ?: apikeyProperties.getProperty(
                "API_URL",
                "https://www.example.com"
            )
        )
        buildConfigField(
            "Boolean",
            "USE_MOCK",
            System.getenv("USE_MOCK") ?: useMockServer.toString()
        )
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.negotiation)
    implementation(libs.kotlinx.serialization)
    implementation(libs.androidx.core.ktx)

    implementation(libs.hilt.android)
    implementation(libs.hilt.compose)
    ksp(libs.hilt.android.compiler)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}