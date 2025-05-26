plugins {
    kotlin("multiplatform") version "2.1.10"
    `maven-publish`
}

group = "org.chorus_oss"
version = "1.0-SNAPSHOT"
description = "Protocol"

repositories {
    mavenLocal()
    mavenCentral()
}

kotlin {
    jvm()
    linuxX64()
    mingwX64()

    // TODO: Add more supported platforms

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.kotlinx.io)

                implementation(libs.kotlin.stdlib)
                implementation(libs.semver)
                implementation(libs.nbt)
                implementation(libs.varlen)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}