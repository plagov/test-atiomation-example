plugins {
    `java-library`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    api(libs.com.codeborne.selenide.appium)
    api(libs.org.junit.jupiter.junit.jupiter.api)
    api(libs.org.junit.jupiter.junit.jupiter.engine)
    api(libs.io.rest.assured.rest.assured)
    api(libs.org.assertj.assertj.core)
    api(libs.net.datafaker.datafaker)
    api(libs.io.qameta.allure.allure.junit5)
    api(libs.io.qameta.allure.allure.rest.assured)
}

group = "io.plagov"
version = "0.0.1-SNAPSHOT"
description = "test-automation-example"
java.sourceCompatibility = JavaVersion.VERSION_17

tasks.withType<Test> {
    useJUnitPlatform()
}
