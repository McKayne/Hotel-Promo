plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.elnico.hotelpromo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.elnico.hotelpromo"
        minSdk = 24
        targetSdk = 34

        val versionMajor = 1
        val versionMinor = 0
        val versionPatch = 0
        val versionSuffix = "first-promo-concept"
        versionCode = versionMajor * 10000 + versionMinor * 1000 + versionPatch
        versionName = "${versionMajor}.${versionMinor}.${versionPatch}-${versionSuffix}"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    applicationVariants.all {
        val variant = this
        variant.outputs
            .map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
            .forEach { output ->
                val outputFileName = "NEOS v${variant.versionName}.apk"
                output.outputFileName = outputFileName
            }
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.7")
    implementation("androidx.navigation:navigation-compose:2.7.7")

    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")

    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // DI
    implementation("io.insert-koin:koin-core:3.2.2")
    implementation("io.insert-koin:koin-android:3.2.3")
    implementation("io.insert-koin:koin-core-ext:3.0.1")
    implementation("io.insert-koin:koin-androidx-compose:3.2.2")

    // Loading
    implementation("com.github.MahboubehSeyedpour:jetpack-loading:1.1.0")

    implementation("com.lightspark:compose-qr-code:1.0.1")


    // Maps
    implementation("org.osmdroid:osmdroid-android:6.1.16")
    implementation("androidx.media3:media3-exoplayer:1.3.0")
    implementation("androidx.media3:media3-ui:1.3.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}