val axonVersion = extra.get("axonVersion")

plugins {
    kotlin("jvm")
}
dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    implementation(project(":core-api"))
    implementation(project(":common"))
    implementation("org.axonframework:axon-spring-boot-starter:$axonVersion")
    implementation("org.axonframework.extensions.kotlin:axon-kotlin:0.1.0")

}