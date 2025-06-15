plugins {
    kotlin("multiplatform") version "2.1.10"
    `maven-publish`
}

group = "org.chorus_oss"
version = "v800-SNAPSHOT"
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
                api(libs.chorus.nbt)

                implementation(libs.chorus.varlen)
            }
        }
    }
}