plugins {
    kotlin("jvm")
}

dependencies {
    implementation("com.windowsazure:Notification-Hubs-java-sdk:0.4.0")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.1")
    annotationProcessor ("org.springframework.boot:spring-boot-configuration-processor")
}
