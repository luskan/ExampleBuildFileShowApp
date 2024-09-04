import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "biz.progmar.examplebuildfileshowapp"
    compileSdk = 34
    buildFeatures.buildConfig = true

    // Prepare path to properties file
    val buildProperties = project.layout.projectDirectory.dir(
        "clients/clientname/Build/Build.properties"
    ).asFile
    print("buildPropertiesPath: $buildProperties")

    // Load properties file
    val properties = Properties()
    file(buildProperties).inputStream().use { properties.load(it) }

    // Get the property
    val appId = properties.getProperty("ONESIGNAL_APP_ID")

    defaultConfig {
        applicationId = "biz.progmar.examplebuildfileshowapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Add the property to BuildConfig
        buildConfigField("String", "ONESIGNAL_APP_ID", "\"$appId\"")
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}