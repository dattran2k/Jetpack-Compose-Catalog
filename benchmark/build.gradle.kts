plugins {
    id("com.android.test")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.dat.benchmark"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        // This benchmark buildType is used for benchmarking, and should function like your
        // release build (for example, with minification on). It"s signed with a debug key
        // for easy local/CI testing.
        create("benchmark") {
            isDebuggable = true
            signingConfig = getByName("debug").signingConfig
            matchingFallbacks += listOf("release")
            proguardFiles("benchmark-rules.pro")
        }
    }

    targetProjectPath = ":app"
    experimentalProperties["android.experimental.self-instrumenting"] = true
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.benchmark.macro)
    implementation(libs.androidx.test.uiautomator)
    implementation(libs.androidx.test.espresso.core)
    implementation(libs.androidx.test.ui)
    implementation(libs.androidx.test.rules)
    implementation(libs.androidx.test.runner)
}

androidComponents {
    beforeVariants(selector().all()) {
        it.enabled = it.buildType == "benchmark"
    }
}