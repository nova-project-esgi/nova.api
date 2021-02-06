val axonVersion = extra.get("axonVersion")
val javaJwtVersion = extra.get("javaJwtVersion")
val jaxbApiVersion = extra.get("jaxbApiVersion")
val authSpringSecurityApiVersion = extra.get("authSpringSecurityApiVersion")
plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation(project(":common"))
    implementation(project(":core-api"))
    implementation(project(":application"))
    implementation(project(":notifications"))
    implementation("com.auth0:java-jwt:$javaJwtVersion")
//    implementation("com.auth0:auth0-spring-security-api:$authSpringSecurityApiVersion")
    implementation("javax.xml.bind:jaxb-api:$jaxbApiVersion")
    implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.axonframework:axon-spring-boot-starter:$axonVersion"){
        exclude(group = "org.axonframework", module = "axon-server-connector")
    }
    implementation("org.axonframework.extensions.kotlin:axon-kotlin:0.1.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("com.thedeanda:lorem:2.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")


}
