plugins {
    `java-library`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

val junitVersion = "5.10.1"
val allureVersion = "2.25.0"

dependencies {
    implementation("com.codeborne:selenide-appium:7.0.4")
    implementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    implementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    implementation("io.rest-assured:rest-assured:5.4.0")
    implementation("org.assertj:assertj-core:3.24.2")
    implementation("net.datafaker:datafaker:2.0.2")
    implementation("io.qameta.allure:allure-junit5:$allureVersion")
    implementation("io.qameta.allure:allure-rest-assured:$allureVersion")
}

group = "io.plagov"
version = "0.0.1-SNAPSHOT"
description = "test-automation-example"
java.sourceCompatibility = JavaVersion.VERSION_17

tasks.withType<Test> {
    useJUnitPlatform()
}
