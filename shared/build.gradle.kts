// Top-level build file where you can add configuration options common to all sub-projects/modules.
//import java.lang.Object

buildscript {
    repositories {
        google()
        mavenCentral()
        mavenLocal()

    }
    dependencies {


        classpath(Deps.android_gradle_plugin)
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}")

        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")

        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.2")
        classpath ("com.google.gms:google-services:4.3.10")
        classpath ("com.google.firebase:firebase-crashlytics-gradle:2.8.0")
    }
}

plugins {
    id("org.jlleitschuh.gradle.ktlint") version Versions.ktlint_gradle_plugin
}

allprojects {
    repositories {
        google()
        mavenCentral()
//        mavenLocal()

        jcenter()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
        maven (url = "https://jitpack.io")
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    ktlint {
        version.set("0.37.2")
        enableExperimentalRules.set(true)
        verbose.set(true)
        filter {
            exclude { it.file.path.contains("build/") }
        }
    }

    afterEvaluate {
        tasks.named("check").configure {
            dependsOn(tasks.getByName("ktlintCheck"))
        }
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
