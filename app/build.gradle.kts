plugins {
    application
    checkstyle
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
    implementation ("info.picocli:picocli:4.7.6")
    annotationProcessor ("info.picocli:picocli-codegen:4.7.6")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.17.1")
    implementation("org.jacoco:jacoco:0.8.12")
}

tasks.test {
    useJUnitPlatform()
}