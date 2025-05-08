import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    kotlin("multiplatform") version "2.1.10"
}

group = "org.chorus_oss.protocol"
version = "1.0-SNAPSHOT"
description = "Protocol"

repositories {
    mavenCentral()
}

@OptIn(ExperimentalWasmDsl::class)
kotlin {
    jvm()
    js()
    wasmJs()
    wasmWasi()
    linuxX64()
    linuxArm64()
    mingwX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlinx.io)
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