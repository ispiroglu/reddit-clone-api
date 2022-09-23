import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
}

group = "com.ispiroglu"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation:2.7.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.11")
    implementation("org.springframework.boot:spring-boot-starter-webflux:2.7.3")


    implementation("org.keycloak:keycloak-spring-boot-starter:18.0.2")
    implementation("org.keycloak:keycloak-admin-client:18.0.2")
    implementation("org.jboss.resteasy:resteasy-client:4.6.0.Final")
    implementation("org.jboss.resteasy:resteasy-client-reactor-netty:4.6.0.Final")
    implementation("org.jboss.resteasy:resteasy-jackson2-provider:4.6.0.Final")
    implementation("org.jboss.resteasy:resteasy-multipart-provider:4.6.0.Final")
    implementation("org.jboss.resteasy:resteasy-jaxb-provider:4.6.0.Final")




    // Map Struct
    implementation ("org.mapstruct:mapstruct:1.5.2.Final")
    annotationProcessor ("org.mapstruct:mapstruct-processor:1.5.2.Final")

    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("io.mockk:mockk:1.12.8")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
