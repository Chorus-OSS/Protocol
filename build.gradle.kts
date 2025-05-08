plugins {
    kotlin("multiplatform") version "2.1.10"
}

group = "org.chorus_oss.protocol"
version = "1.0-SNAPSHOT"
description = "Protocol"

repositories {
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
                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlinx.io)
                implementation(libs.semver)
                implementation(libs.knbt)
            }
        }

        val commonTest by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}