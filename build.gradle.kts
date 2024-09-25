plugins {
    alias(pluginLibs.plugins.android.library)
    alias(pluginLibs.plugins.kotlin.android)
}

android {
    namespace = "com.example.viewmodel"
    compileSdk = sdk.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = sdk.versions.minSdk.get().toInt()
        targetSdk = sdk.versions.targetSdk.get().toInt()

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
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.activity.ktx)
    implementation(libs.fragment.ktx)
    implementation(libs.constraintlayout)
    implementation(libs.coroutines)
    implementation(libs.coroutines.android)

    androidTestImplementation(testLibs.coroutines.test)
    androidTestImplementation(testLibs.core.ktx)
    androidTestImplementation(testLibs.espresso)
    androidTestImplementation(testLibs.espresso.intents)
    androidTestImplementation(testLibs.android.junit.ktx)
}
