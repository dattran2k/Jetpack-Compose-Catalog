@file:Suppress("UnstableApiUsage")

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.dagger.hilt.library)
    alias(libs.plugins.protobuf)
    kotlin("kapt")
}

android {
    namespace = "com.dat.core.datastore"

    defaultConfig {
        consumerProguardFiles("consumer-consumer-proguard-rules.pro")
    }
}
// Setup protobuf configuration, generating lite Java and Kotlin classes
protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    //data store
    implementation(project(":core:model"))
    // Coroutines
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.android)
    implementation(libs.androidx.datastore)
    implementation(libs.protobuf.kotlin.lite)
    implementation(libs.dagger.hilt.library)
    kapt(libs.dagger.hilt.compiler)

}

