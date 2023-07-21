@file:Suppress("UnstableApiUsage")

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.dagger.hilt.library)
    kotlin("kapt")
}

android {
    namespace = "com.dat.core.data"
}

dependencies {
    implementation(project(":core:datastore"))
    implementation(project(":core:model"))
    implementation(libs.dagger.hilt.library)
    kapt(libs.dagger.hilt.compiler)
}