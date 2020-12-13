val jaxbApiVersion = extra.get("jaxbApiVersion")
val javaJwtVersion = extra.get("javaJwtVersion")

plugins {
    kotlin("jvm")
}
dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("javax.xml.bind:jaxb-api:$jaxbApiVersion")
    implementation("com.auth0:java-jwt:$javaJwtVersion")
}
