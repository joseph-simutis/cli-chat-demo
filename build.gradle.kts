plugins {
    kotlin("jvm") version "2.0.0"
    application
}

group = "io.github.josephsimutis"
version = "DEV"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.ajalt.clikt:clikt:5.0.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass = "io.github.josephsimutis.MainKt"
}