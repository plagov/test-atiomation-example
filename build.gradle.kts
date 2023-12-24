plugins {
    java
    id("io.qameta.allure") version "2.11.2"
}

repositories {
    mavenLocal()
    mavenCentral()
}

val junitVersion = "5.10.1"
val allureVersion = "2.25.0"
val aspectJVersion = "1.9.20.1"

val agent: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = true
}

allure {
    adapter {
        allureJavaVersion.set(allureVersion)
        aspectjWeaver.set(true)
    }
}

dependencies {
    implementation("com.codeborne:selenide-appium:7.0.4")
    implementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    implementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    implementation("io.rest-assured:rest-assured:5.4.0")
    implementation("org.assertj:assertj-core:3.24.2")
    implementation("net.datafaker:datafaker:2.0.2")
    implementation("io.qameta.allure:allure-junit5:$allureVersion")
    implementation("io.qameta.allure:allure-rest-assured:$allureVersion")
    implementation("io.qameta.allure:allure-selenide:$allureVersion")
    agent("org.aspectj:aspectjweaver:${aspectJVersion}")
}

group = "io.plagov"
version = "0.0.1-SNAPSHOT"
description = "test-automation-example"
java.sourceCompatibility = JavaVersion.VERSION_17

tasks.withType<Test> {
    dependsOn("clean")
    val includeTags = System.getProperty("includeTags")
    useJUnitPlatform {
        if (!includeTags.isNullOrBlank()) {
            includeTags(includeTags)
        }
    }
    jvmArgs = listOf("-javaagent:${agent.singleFile}")
}
