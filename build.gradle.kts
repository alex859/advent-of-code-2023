plugins {
    kotlin("jvm") version "1.9.21"
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
