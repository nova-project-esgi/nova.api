val axonVersion = extra.get("axonVersion")
plugins {
    id("org.springframework.boot")
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation(project(":choices"))
    implementation(project(":difficulties"))
    implementation(project(":languages"))
    implementation(project(":events"))
    implementation(project(":games"))
    implementation(project(":resources"))
    implementation(project(":users"))
    implementation(project(":common"))
    implementation(project(":query"))
    implementation(project(":core-api"))
    implementation(project(":web"))
    implementation(project(":files:files-manager"))
    implementation(project(":files:files"))
    implementation(project(":files:files-query"))
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.axonframework:axon-spring-boot-starter:$axonVersion") {
        exclude(group = "org.axonframework", module = "axon-server-connector")
    }
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.microsoft.sqlserver:mssql-jdbc")
}

tasks.getByName<org.springframework.boot.gradle.tasks.run.BootRun>("bootRun") {
    args = mutableListOf("--spring.profiles.active=prod")
}
