import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    //네비게이션 인자 전달
    id ("kotlin-parcelize")
}

android {
    namespace = "com.curic4t.android.workout"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.curic4t.android.workout"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //buildConfigField("String", "WEATHER_API_KEY", properties["WEATHER_API_KEY"])
        buildConfigField("String", "WEATHER_API_KEY", getApiKey("WEATHER_API_KEY"))
        buildConfigField("String", "WEATHER_API_KEY_ENCODED", getApiKey("WEATHER_API_KEY_ENCODED"))
        buildConfigField("String","KAKAO_NATIVE_KEY",getApiKey("KAKAO_NATIVE_KEY"))
    }

    buildFeatures {
        //api 키를 사용하기 위해 buildConfig를 사용
        buildConfig = true
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
        viewBinding = true
        dataBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
}

fun getApiKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //livedata
    implementation(libs.androidx.runtime.livedata)


    //splash
    implementation (libs.androidx.core.splashscreen)


    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp.urlconnection)


    //gps
    implementation(libs.play.services.location)

    //카카오 맵
    implementation (libs.android)



}