val axonVersion = extra.get("axonVersion")

plugins {
    kotlin("jvm")
}
dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":core-api"))
    implementation(project(":files-core-api"))
    implementation(project(":common"))
    implementation(project(":files-infra"))
    implementation("org.axonframework:axon-spring-boot-starter:$axonVersion")
    implementation("org.axonframework.extensions.kotlin:axon-kotlin:0.1.0")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter")

}



