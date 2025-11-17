plugins {
    id("java")
}


group = "org.calidadsoftware"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.serenity-bdd.info") }
}

dependencies {
    testImplementation("junit:junit:4.13.2")

    testImplementation("net.serenity-bdd:serenity-core:4.3.4")
    testImplementation("net.serenity-bdd:serenity-junit:4.3.4")
    testImplementation("net.serenity-bdd:serenity-screenplay:4.3.4")
    testImplementation("net.serenity-bdd:serenity-screenplay-webdriver:4.3.4")
    testImplementation("net.serenity-bdd:serenity-cucumber:4.3.4")

    testImplementation("io.cucumber:cucumber-java:7.11.0")
    testImplementation("io.cucumber:cucumber-junit:7.11.0")

    testImplementation("org.seleniumhq.selenium:selenium-java:4.22.0")
    testImplementation("io.github.bonigarcia:webdrivermanager:5.9.1")

    implementation("org.slf4j:slf4j-api:2.0.13")
    implementation("org.slf4j:slf4j-simple:2.0.13")

    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.10.0")
}

tasks.test {
    useJUnit {
        includeCategories("org.junit")
    }
}