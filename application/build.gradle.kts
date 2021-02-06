val axonVersion = extra.get("axonVersion")

plugins {
    kotlin("jvm")
}
dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":core-api"))
    implementation(project(":common"))
    implementation(project(":files:files-infra"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.axonframework:axon-spring-boot-starter:$axonVersion") {
        exclude(group = "org.axonframework", module = "axon-server-connector")
    }
    implementation("org.axonframework.extensions.kotlin:axon-kotlin:0.1.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation ("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
//    testImplementation("org.mockito:mockito-core:3.3.3")
}