plugins {
    application
    checkstyle
    jacoco
    id("java")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

application {
    mainClass = "./src/main/java/hexlet/code/App.java"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.0-M1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.skyscreamer:jsonassert:1.5.3")
    implementation("info.picocli:picocli:4.7.6")
    annotationProcessor("info.picocli:picocli-codegen:4.7.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.1")
    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.1")
    implementation("org.jacoco:org.jacoco.core:0.8.12")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
    }
}