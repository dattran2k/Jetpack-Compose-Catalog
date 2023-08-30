plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.dat.core.designsystem"

    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui.ui)
    implementation(libs.androidx.compose.material.material3)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.lottile)
    implementation(libs.reorderable)
}