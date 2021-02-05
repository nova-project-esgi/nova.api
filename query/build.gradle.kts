val axonVersion = extra.get("axonVersion")

plugins {
    kotlin("jvm")
    kotlin("plugin.jpa") version "1.4.10"
}


dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation(project(":common"))
    implementation(project(":core-api"))
    implementation("org.projectlombok:lombok")
        implementation("org.axonframework:axon-spring-boot-starter:$axonVersion"){
        exclude(group = "org.axonframework", module = "axon-server-connector")
    }
    implementation("org.axonframework.extensions.kotlin:axon-kotlin:0.1.0")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

}
