plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "university.homework"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    // PostgreSQL драйвер
    implementation("org.postgresql:postgresql:42.7.4")

    // Apache POI для работы с Excel
    implementation("org.apache.poi:poi-ooxml:5.2.2")

    // Apache XMLBeans для работы с XML
    implementation("org.apache.xmlbeans:xmlbeans:5.1.1")

    // Commons Logging (логгирование)
    implementation("commons-logging:commons-logging:1.2")

    // Log4j2 для логирования
    implementation("org.apache.logging.log4j:log4j-api:2.20.0")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<JavaExec> {
    systemProperty("file.encoding", "UTF-8")
}

application {
    mainClass.set("university.homework.Main")
}
