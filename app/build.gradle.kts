plugins {
    application
    id("java")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

application {
    mainClass = "./src/main/java/App.java"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.0-M1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}