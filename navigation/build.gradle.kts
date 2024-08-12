plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id ("kotlin-parcelize")
}

android {
    namespace = "com.example.navigation"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures{
        compose = true
    }
    composeOptions{
        kotlinCompilerExtensionVersion = "1.5.9"
    }
}

kotlin{
    jvmToolchain{
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.compose.bom)
    implementation(libs.ui)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.core.ktx)
    // Parcelize: add in plugin and implementation
    implementation (libs.kotlin.stdlib)

    // Multi statck: for used Immutable List for example
    api(libs.kotlinx.collections.immutable)

    //  20-07-2024 viewmodel
    api(libs.androidx.lifecycle.viewmodel.compose)

    testImplementation(libs.junit)
}