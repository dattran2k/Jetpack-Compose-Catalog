@file:Suppress("UnstableApiUsage")

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.cacheFixPlugin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.android.dagger.hilt.library)
    alias(libs.plugins.protobuf)
    kotlin("kapt")
}

android {
    namespace = "com.dat.jetpackcomposecatalog"

    defaultConfig {
        applicationId = "com.dat.jetpackcomposecatalog"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles("proguard-rules.pro")
        }
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composecompiler.get()
    }
    packaging {
        packagingOptions.resources.excludes += setOf(
            // Exclude AndroidX version files
            "META-INF/*.version",
            // Exclude consumer proguard files
            "META-INF/proguard/*",
            // Exclude the Firebase/Fabric/other random properties files
            "/*.properties",
            "fabric/*.properties",
            "META-INF/*.properties",
        )
    }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:designsystem"))
    implementation(project(":ui"))

    implementation(libs.androidx.activity.activity)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.work.runtime)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material.material)
    implementation(libs.androidx.compose.material.material3)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.ui.tooling)

    // hilt
    implementation(libs.androidx.hilt.navigationcompose)
    implementation(libs.androidx.hilt.work)
    implementation(libs.dagger.hilt.library)
    kapt(libs.dagger.hilt.compiler)

    // testing
    testImplementation(platform(libs.androidx.compose.bom))
    testImplementation(libs.androidx.test.ui)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.test.ui)
    implementation(libs.androidx.test.manifest)
    // Coroutines
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.android)

    // Coil
    implementation(libs.accompanist.coil)
    implementation(libs.lottile)
    implementation("org.burnoutcrew.composereorderable:reorderable:0.9.6")
}
