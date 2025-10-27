plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.38.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.13.4")
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("com.google.api-client:google-api-client:2.0.0")
    testImplementation("com.google.oauth-client:google-oauth-client-jetty:1.34.1")
    testImplementation("com.google.apis:google-api-services-gmail:v1-rev20220404-2.0.0")
//    testImplementation("commons-codec:commons-codec:1.15")
}

tasks.test {
    useJUnitPlatform()
}