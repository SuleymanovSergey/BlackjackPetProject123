plugins {
    id("java")
}

group = "org.blackjack"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


tasks.test {
    useJUnitPlatform()
}