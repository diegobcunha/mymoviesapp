// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.5")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven { setUrl("https://dl.bintray.com/populov/maven") }
        maven { setUrl("https://jitpack.io") }
        maven { setUrl("https://zendesk.jfrog.io/zendesk/repo") }
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
        maven { setUrl("https://oss.sonatype.org/content/repositories/snapshots") }
        maven { setUrl("https://dl.bintray.com/apollographql/android") }
    }
    configurations.all {
        resolutionStrategy.force("org.objenesis:objenesis:2.6")
    }
}

plugins {
    `kotlin-dsl`
}