plugins {
    id("java")
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "ru.kpfu.itis.sorokin"
version = "1.0-SNAPSHOT"

//val springVersion: String by project
//val springDataVersion: String by project
//val hibernateVersion: String by project
val jakartaVersion: String by project
val postgresVersion: String by project
//val freemarkerVersion: String by project
//val hikariVersion: String by project
val springSecurityVersion: String by project

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.postgresql:postgresql:$postgresVersion")
    implementation("org.springframework.security:spring-security-taglibs:${springSecurityVersion}")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-freemarker")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
//    implementation("javax.mail:javax.mail-api:1.6.2")
    implementation("org.springframework.boot:spring-boot-starter-mail")
}

//application {
//    mainClass = "ru.kpfu.itis.sorokin.Main"
//}

tasks.test {
    useJUnitPlatform()
}