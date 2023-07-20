pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.google.com")
    }
}

rootProject.name = "JetpackCompose Catalog"
include(":app")
include(":benchmark")
include(":core")
include(":core:datastore")
include(":core:data")
include(":core:model")
