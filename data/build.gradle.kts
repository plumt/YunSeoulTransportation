plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("kotlin-kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    implementation(project(":domain"))

    // hilt
    implementation("com.google.dagger:hilt-core:2.49")
    kapt("com.google.dagger:hilt-compiler:2.49")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.7.1")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.7.1")
    implementation("com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0")

    // 크롤링
    implementation("org.jsoup:jsoup:1.11.3")
}