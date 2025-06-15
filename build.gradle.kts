import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinMultiplatform
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    kotlin("multiplatform") version "2.1.10"
    id("org.jetbrains.dokka") version "2.0.0"
    id("com.vanniktech.maven.publish") version "0.32.0"
}

group = "org.chorus_oss"
version = "v800.0"
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

    mavenPublishing {
        publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
        signAllPublications()

        coordinates(
            group.toString(),
            "protocol",
            version.toString()
        )

        pom {
            name = "Protocol"
            description = "Minecraft: Bedrock protocol library for Kotlin Multiplatform"
            inceptionYear = "2025"
            url = "https://github.com/Chorus-OSS/Protocol"
            licenses {
                license {
                    name = "The Apache License, Version 2.0"
                    url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                    distribution = "repo"
                }
            }
            developers {
                developer {
                    id = "omniacdev"
                    name = "OmniacDev"
                    url = "https://github.com/OmniacDev"
                    email = "omniacdev@chorus-oss.org"
                }
            }
            scm {
                url = "https://github.com/Chorus-OSS/Protocol"
                connection = "scm:git:git://github.com/Chorus-OSS/Protocol.git"
                developerConnection = "scm:git:ssh://github.com/Chorus-OSS/Protocol.git"
            }
            issueManagement {
                system = "GitHub Issues"
                url = "https://github.com/Chorus-OSS/Protocol/issues"
            }
        }

        configure(
            KotlinMultiplatform(
                javadocJar = JavadocJar.Dokka("dokkaHtml"),
                sourcesJar = true,
                androidVariantsToPublish = emptyList(),
            )
        )
    }
}