import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("kotlinx-serialization")
    id("com.android.library")
}

android {
    compileSdk = Versions.compile_sdk
    defaultConfig {
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    lint {
        isWarningsAsErrors = true
        isAbortOnError = true
    }
    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }
}

version = "1.0"

kotlin {
    android()
    ios()

    version = "1.1"

    sourceSets {
        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlin.RequiresOptIn")
                useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }
    }


    sourceSets["commonMain"].dependencies {
        implementation(Deps.Ktor.commonCore)
        implementation(Deps.Ktor.commonJson)
        implementation(Deps.Ktor.commonLogging)
        implementation(Deps.Coroutines.common)
        implementation(Deps.stately)
        implementation(Deps.multiplatformSettings)
        implementation(Deps.koinCore)
        implementation(Deps.Ktor.commonSerialization)
        implementation(Deps.kotlinxDateTime)
        api(Deps.kermit)
    }

    sourceSets["commonTest"].dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.3")

    }

    sourceSets["androidMain"].dependencies {
        implementation(kotlin("stdlib", Versions.kotlin))
        implementation(Deps.Coroutines.android)
        implementation(Deps.AndroidX.lifecycle_viewmodel_extensions)
        implementation(Deps.Ktor.androidCore)

    }

    sourceSets["androidTest"].dependencies {
        implementation(kotlin("test-junit"))
        implementation("junit:junit:4.13.2")
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.3")

    }

    sourceSets["iosMain"].dependencies {
        implementation(Deps.Ktor.ios)
        implementation(Deps.Coroutines.common) {
            version {
                strictly(Versions.coroutines)
            }
        }

    }


    cocoapods {
        summary = "Common library for the KaMP starter kit"
        homepage = "https://github.com/touchlab/KaMPKit"
    }

    // Configure the framework which is generated internally by cocoapods plugin
    targets.withType<KotlinNativeTarget> {
        binaries.withType<Framework> {
            isStatic = false // SwiftUI preview requires dynamic framework
            export(Deps.kermit)
            transitiveExport = true
            // TODO ADDED BUT NOT USED
//            embedBitcode(BITCODE)
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

}
