plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("androidx.navigation.safeargs") version "2.7.4" apply false
}

android {
    namespace = "com.example.hqawasomeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.hqawasomeapp"
        minSdk = 31
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    /**HABILIDAR dataBinding*/
    dataBinding {
        enable = true
    }

    /**DESATIVADO PARA TROCAR POR DATABINDING*/
    //buildFeatures {viewBinding = true }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Lifecycle components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.6.2")
    implementation("androidx.fragment:fragment-ktx:1.6.1")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.core:core-ktx:1.12.0")

    // navigation components
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.3")

    // UI
    implementation("com.google.android.material:material:1.9.0")
    implementation("me.relex:circleindicator:2.1.6")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("org.imaginativeworld.whynotimagecarousel:whynotimagecarousel:2.1.0")        //Carousel de imagens

    // RETROFIT e MOSHI
    implementation("com.squareup.retrofit2:retrofit:2.9.0")                 // RETROFIT
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")                // MOSHI
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")          // RETORFIT com MOSHI
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")                  //KAP
    implementation("com.github.bumptech.glide:glide:4.13.1")                //GLIDE
    annotationProcessor("com.github.bumptech.glide:compiler:4.13.0")        //GLIDE

    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
}

buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
    }
}