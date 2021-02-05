val javaxValidationVersion = extra.get("javaxValidationVersion")
val hibernateValidatorVersion = extra.get("hibernateValidatorVersion")
val axonVersion = extra.get("axonVersion")
plugins {
    kotlin("jvm")
}

dependencies {
//		implementation("org.projectlombok:lombok")
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation(project(":common"))
        implementation("org.axonframework:axon-spring-boot-starter:$axonVersion"){
        exclude(group = "org.axonframework", module = "axon-server-connector")
    }
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("javax.validation:validation-api:$javaxValidationVersion")
    implementation("org.hibernate:hibernate-validator:$hibernateValidatorVersion")
}