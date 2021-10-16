plugins {
    id("com.android.application") version Dependencies.AndroidPlugin.VERSION
    kotlin("android") version Dependencies.KotlinPlugin.VERSION
    kotlin("plugin.parcelize") version Dependencies.KotlinPlugin.VERSION
    kotlin("kapt") version Dependencies.KotlinPlugin.VERSION
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
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.AndroidX.Compose.VERSION
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    lint {
        checkDependencies = true
    }
}

dependencies {
    implementation(project(Dependencies.Project.Feature.monsterList))
    implementation(project(Dependencies.Project.Feature.monsterDetails))
    implementation(project(Dependencies.Project.Shared.uiCore))
    implementation(project(Dependencies.Project.Shared.navigationCore))
    implementation(project(Dependencies.Project.Shared.monsterApi))

//    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.AndroidX.Core.coreKtx)

    implementation(Dependencies.AndroidX.Compose.ui)
    implementation(Dependencies.AndroidX.Compose.material)
    implementation(Dependencies.AndroidX.Compose.uiToolingPreview)
    implementation(Dependencies.AndroidX.Compose.compiler)
    androidTestImplementation(Dependencies.AndroidX.Compose.uiTestJunit)
    debugImplementation(Dependencies.AndroidX.Compose.uiTooling)

    implementation(Dependencies.AndroidX.Activity.activityCompose)

//    testImplementation(Dependencies.Junit.core)
//    androidTestImplementation(Dependencies.AndroidX.TestExt.junit)
//    androidTestImplementation(Dependencies.AndroidX.TestEspresso.espressoCore)

    implementation(Dependencies.Dagger.core)
    kapt(Dependencies.Dagger.daggerCompiler)
    implementation(Dependencies.Dagger.daggerAndroidSupport)
    kapt(Dependencies.Dagger.daggerAndroidProcessor)

    implementation(Dependencies.KotlinX.kotlinxCoroutinesAndroid)

    implementation(Dependencies.AndroidX.Lifecycle.lifecycleRuntimeKtx)
    implementation(Dependencies.AndroidX.Lifecycle.lifecycleViewModelKtx)
    implementation(Dependencies.AndroidX.Lifecycle.lifecycleLiveDataKtx)
    implementation(Dependencies.AndroidX.Lifecycle.lifecycleLiveDataCoreKtx)
    implementation(Dependencies.AndroidX.Lifecycle.lifecycleViewModelCompose)
    implementation(Dependencies.AndroidX.Lifecycle.lifecycleViewModelSavedState)

    //эти зависимосте здесь скорее всего не нужны и нужно будет их перененсти в другой модуль
    implementation(Dependencies.AndroidX.Room.roomKtx)
    implementation(Dependencies.AndroidX.Room.roomRuntime)
    kapt(Dependencies.AndroidX.Room.roomCompiler)
    androidTestImplementation(Dependencies.AndroidX.Room.roomTesting)
//
//    implementation(Dependencies.AndroidX.Collections.collectionKtx)

    implementation(Dependencies.AndroidX.Navigation.navigationRuntimeKtx)
    implementation(Dependencies.AndroidX.Navigation.navigationUiKtx)
    implementation(Dependencies.AndroidX.Navigation.composeNavigation)

    //эти зависимосте здесь скорее всего не нужны и нужно будет их перененсти в другой модуль
    implementation(Dependencies.Retrofit2.retrofit)
    implementation(Dependencies.Retrofit2.converterMoshi)
    implementation(Dependencies.OkHttp3.okhttp)
    implementation(Dependencies.OkHttp3.loggingInterceptor)

    implementation(Dependencies.Orbit.viewModel)

//    implementation(Dependencies.Accompanist.systemUiController)
}
