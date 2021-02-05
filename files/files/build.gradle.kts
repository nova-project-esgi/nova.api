val axonVersion = extra.get("axonVersion")

plugins {
    kotlin("jvm")
}
dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    implementation(project(":files:files-core-api"))
    implementation(project(":core-api"))
    implementation(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.axonframework:axon-spring-boot-starter:$axonVersion"){
        exclude(group = "org.axonframework", module = "axon-server-connector")
    }
    implementation("org.axonframework.extensions.kotlin:axon-kotlin:0.1.0")

}