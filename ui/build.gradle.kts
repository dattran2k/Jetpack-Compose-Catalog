@file:Suppress("UnstableApiUsage")

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.dagger.hilt.library)
    kotlin("kapt")
}

android {
    namespace = "com.dat.ui"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composecompiler.get()
    }
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:model"))
    implementation(project(":core:data"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.ui.ui)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material.material3)
    implementation(libs.lottile)

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(libs.accompanist.coil)
    implementation(libs.lottile)
    implementation("org.burnoutcrew.composereorderable:reorderable:0.9.6")
    // hilt
    implementation(libs.androidx.hilt.navigationcompose)
    implementation(libs.androidx.hilt.work)
    implementation(libs.dagger.hilt.library)
    kapt(libs.dagger.hilt.compiler)

    // Coroutines
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.android)
    // Coroutine Lifecycle Scopes
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
}