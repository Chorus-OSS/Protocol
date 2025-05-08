plugins {
    kotlin("jvm") version "2.1.10"
}

group = "org.chorus_oss.protocol"
version = "1.0-SNAPSHOT"
description = "Protocol"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.7.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}