val javaxValidationVersion = extra.get("javaxValidationVersion")
val axonVersion = extra.get("axonVersion")
plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation(project(":common"))
    implementation(project(":core-api"))
    implementation("org.axonframework:axon-spring-boot-starter:$axonVersion")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("javax.validation:validation-api:$javaxValidationVersion")
}