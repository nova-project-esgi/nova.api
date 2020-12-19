val axonVersion = extra.get("axonVersion")
plugins {
    id("org.springframework.boot")
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation(project(":core"))
    implementation(project(":common"))
    implementation(project(":query"))
    implementation(project(":core-api"))
    implementation(project(":web"))
    implementation(project(":file-manager"))
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.axonframework:axon-spring-boot-starter:$axonVersion")
    implementation("org.springframework.boot:spring-boot-starter")
//    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.microsoft.sqlserver:mssql-jdbc")
}

