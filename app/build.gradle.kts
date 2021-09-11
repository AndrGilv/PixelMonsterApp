plugins {
    id("com.android.application") version Libraries.AndroidPlugin.VERSION
    kotlin("android") version Libraries.KotlinPlugin.VERSION
    kotlin("plugin.parcelize") version Libraries.KotlinPlugin.VERSION
    kotlin("kapt") version Libraries.KotlinPlugin.VERSION
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.example.pixelmonsterapp3"
        minSdk = 26
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libraries.AndroidX.Compose.VERSION
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.AndroidX.Core.coreKtx)

    implementation(Libraries.AndroidX.Compose.ui)
    implementation(Libraries.AndroidX.Compose.material)
    implementation(Libraries.AndroidX.Compose.uiToolingPreview)
    implementation(Libraries.AndroidX.Compose.compiler)
    androidTestImplementation(Libraries.AndroidX.Compose.uiTestJunit)
    debugImplementation(Libraries.AndroidX.Compose.uiTooling)

    implementation(Libraries.AndroidX.Activity.activityCompose)

    testImplementation(Libraries.Junit.core)
    androidTestImplementation(Libraries.AndroidX.TestExt.junit)
    androidTestImplementation(Libraries.AndroidX.TestEspresso.espressoCore)

    implementation(Libraries.Dagger.core)
    kapt(Libraries.Dagger.daggerCompiler)
    implementation(Libraries.Dagger.daggerAndroidSupport)
    kapt(Libraries.Dagger.daggerAndroidProcessor)

    implementation(Libraries.KotlinX.kotlinxCoroutinesAndroid)

    implementation(Libraries.AndroidX.Lifecycle.lifecycleRuntimeKtx)
    implementation(Libraries.AndroidX.Lifecycle.lifecycleViewModelKtx)
    implementation(Libraries.AndroidX.Lifecycle.lifecycleRuntimeKtx)
    implementation(Libraries.AndroidX.Lifecycle.lifecycleLiveDataKtx)
    implementation(Libraries.AndroidX.Lifecycle.lifecycleLiveDataCoreKtx)
    implementation(Libraries.AndroidX.Lifecycle.lifecycleReactiveStreamsKtx)
    implementation(Libraries.AndroidX.Lifecycle.lifecycleViewModelCompose)
    implementation(Libraries.AndroidX.Lifecycle.lifecycleViewModelSavedState)

    implementation(Libraries.AndroidX.Room.roomKtx)
    implementation(Libraries.AndroidX.Room.roomRuntime)
    kapt(Libraries.AndroidX.Room.roomCompiler)
    androidTestImplementation(Libraries.AndroidX.Room.roomTesting)

    implementation(Libraries.AndroidX.Collections.collectionKtx)
    implementation(Libraries.AndroidX.Core.coreKtx)
    implementation(Libraries.AndroidX.Lifecycle.lifecycleLiveDataCoreKtx)
    implementation(Libraries.AndroidX.Lifecycle.lifecycleReactiveStreamsKtx)
    implementation(Libraries.AndroidX.Lifecycle.lifecycleViewModelCompose)

    implementation(Libraries.AndroidX.Navigation.navigationRuntimeKtx)
    implementation(Libraries.AndroidX.Navigation.navigationFragmentKtx)
    implementation(Libraries.AndroidX.Navigation.navigationUiKtx)
    implementation(Libraries.AndroidX.Navigation.composeNavigation)

    implementation(Libraries.Retrofit2.retrofit)
    implementation(Libraries.Retrofit2.converterMoshi)
    implementation(Libraries.OkHttp3.okhttp)
    implementation(Libraries.OkHttp3.loggingInterceptor)

    implementation(Libraries.Orbit.viewModel)
}
