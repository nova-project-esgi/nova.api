val axonVersion = extra.get("axonVersion")
plugins {
    id("org.springframework.boot")
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation(project(":choices-write"))
    implementation(project(":difficulties-write"))
    implementation(project(":languages-write"))
    implementation(project(":events-write"))
    implementation(project(":games-write"))
    implementation(project(":resources-write"))
    implementation(project(":users-write"))
    implementation(project(":common"))
    implementation(project(":query"))
    implementation(project(":core-api"))
    implementation(project(":web"))
    implementation(project(":files-manager"))
    implementation(project(":files-write"))
    implementation(project(":files-query"))
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.axonframework:axon-spring-boot-starter:$axonVersion")
    implementation("org.springframework.boot:spring-boot-starter")
//    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.microsoft.sqlserver:mssql-jdbc")
}

tasks.getByName<org.springframework.boot.gradle.tasks.run.BootRun>("bootRun") {
    args = mutableListOf("--spring.profiles.active=prod")
}
