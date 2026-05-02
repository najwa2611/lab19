plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.roommvvmdemo"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.roommvvmdemo"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

// Version minimaliste qui existe
val room_version = "2.2.6"

dependencies {
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // UNIQUEMENT ces deux dépendances Room (pas room-livedata)
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // ViewModel et LiveData (ces bibliothèques existent)
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.4.1")
    implementation("androidx.lifecycle:lifecycle-livedata:2.4.1")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    // RecyclerView & CardView
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.cardview:cardview:1.0.0")

    // Tests
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}