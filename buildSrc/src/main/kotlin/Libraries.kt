object Libraries {

    object AndroidX {
        object TestExt {
            private const val VERSION = "1.1.3"
            const val junit = "androidx.test.ext:junit:$VERSION"
        }

        object TestEspresso {
            private const val VERSION = "3.4.0"
            const val espressoCore = "androidx.test.espresso:espresso-core:$VERSION"
        }

        object Core {
            private const val VERSION = "1.7.0-alpha02"
            const val coreKtx = "androidx.core:core-ktx:$VERSION"
        }

        object Compose {
            const val VERSION = "1.1.0-alpha03"

            const val ui = "androidx.compose.ui:ui:$VERSION"
            const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$VERSION"
            const val material = "androidx.compose.material:material:$VERSION"
            const val uiTestJunit = "androidx.compose.ui:ui-test-junit4:$VERSION"
            const val uiTooling = "androidx.compose.ui:ui-tooling:$VERSION"
            const val compiler = "androidx.compose.compiler:compiler:$VERSION"
        }

        object Lifecycle {
            private const val VERSION = "2.4.0-alpha03"
            const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$VERSION"
            const val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07"
            const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$VERSION"
            const val lifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$VERSION"
            const val lifecycleLiveDataCoreKtx = "androidx.lifecycle:lifecycle-livedata-core-ktx:$VERSION"
            const val lifecycleReactiveStreamsKtx = "androidx.lifecycle:lifecycle-reactivestreams-ktx:$VERSION"
            const val lifecycleViewModelSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$VERSION"
        }

        object Activity {
            private const val VERSION = "1.3.1"
            const val activityCompose = "androidx.activity:activity-compose:$VERSION"
        }

        object Room {
            private const val VERSION = "2.4.0-alpha04"
            const val roomKtx = "androidx.room:room-ktx:$VERSION" // для Flow
            const val roomRuntime = "androidx.room:room-runtime:$VERSION" // для Flow
            const val roomCompiler = "androidx.room:room-compiler:$VERSION" // kapt
            const val roomTesting = "androidx.room:room-testing:$VERSION"
        }

        object Collections {
            private const val VERSION = "1.2.0-alpha01"
            const val collectionKtx = "androidx.collection:collection-ktx:$VERSION"
        }

        object Navigation {
            private const val VERSION = "2.4.0-alpha08"
            const val composeNavigation = "androidx.navigation:navigation-compose:$VERSION"
            const val navigationRuntimeKtx = "androidx.navigation:navigation-runtime-ktx:$VERSION"
            const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:$VERSION"
            const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$VERSION"
        }
    }

    object Junit {
        private const val VERSION = "4.13.2"
        const val core = "junit:junit:$VERSION"
    }

    object Dagger {
        private const val VERSION = "2.38.1"
        const val core = "com.google.dagger:dagger:$VERSION"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:$VERSION"
        const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:$VERSION"
        const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:$VERSION"
    }

    object KotlinX {
        private const val VERSION = "1.5.1"
        const val kotlinxCoroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$VERSION"
    }

    object KotlinPlugin {
        const val VERSION = "1.5.30"
    }

    object AndroidPlugin {
        const val VERSION = "7.1.0-alpha12"
    }

    object Retrofit2 {
        private const val VERSION = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$VERSION"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:$VERSION"
    }

    object OkHttp3 {
        private const val VERSION = "5.0.0-alpha.2"
        const val okhttp = "com.squareup.okhttp3:okhttp:$VERSION"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$VERSION"
    }

    object Orbit {
        private const val VERSION = "4.2.0"
        const val viewModel = "org.orbit-mvi:orbit-viewmodel:$VERSION"
    }

    object Accompanist {
        private const val VERSION = "0.18.0"
        const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:$VERSION"
    }
}
