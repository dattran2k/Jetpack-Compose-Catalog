pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.google.com")
    }
}

rootProject.name = "JetpackCompose Catalog"
include(":app",)
