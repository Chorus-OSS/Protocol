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
    implementation("io.netty:netty-buffer:4.2.0.Final")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}