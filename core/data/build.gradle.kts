import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.serialization)
    alias(libs.plugins.hiltAndroid)
    kotlin("kapt")
}

// Change this property if you want to switch to the real server implementation
val useMockServer = true

// Make sure your secrets.properties file is in the root project directory and contains the following key/value pairs:
// API_KEY=yourApiKey
// API_URL=https://yourApiUrl.com
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
            apikeyProperties.getProperty("API_KEY", "defaultApiKey")
        )
        buildConfigField(
            "String",
            "API_URL",
            apikeyProperties.getProperty("API_URL", "https://www.example.com")
        )
        buildConfigField(
            "Boolean",
            "USE_MOCK",
            useMockServer.toString()
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

    kapt(libs.hilt.android.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}