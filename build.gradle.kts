val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val guiceVersion: String by project
val arrowVersion: String by project
val autoServiceVersion: String by project
val jacksonVersion: String by project
val jupiterVersion: String by project
val kotlinPoetVersion: String by project

plugins {
    kotlin("jvm") version "1.4.0"
    kotlin("kapt") version "1.4.0"
}

configure(subprojects) {
    group = "com.esgi"
    version = "0.0.1"

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "kotlin")

    repositories {
        mavenLocal()
        jcenter()
    }

    dependencies {
        api("com.github.pozo:mapstruct-kotlin:1.3.1.2")
        kapt("com.github.pozo:mapstruct-kotlin-processor:1.3.1.2")
        implementation(kotlin("stdlib-jdk8"))
        implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
        testImplementation("org.junit.jupiter:junit-jupiter-api:$jupiterVersion")
        testImplementation("org.junit.jupiter:junit-jupiter-params:$jupiterVersion")
        testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jupiterVersion")
        tasks.test {
            useJUnitPlatform()
        }
    }
    tasks {
        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()

        }
    }
}

configure(subprojects.filter { it.name != "common" }) {
    dependencies {
        implementation(project(":common"))
    }
}

configure(subprojects.filter { it.name == "exposed" || it.name == "web" }) {
    dependencies {
        kapt("org.mapstruct:mapstruct-processor:1.4.0.Final")
        implementation("org.mapstruct:mapstruct:1.4.0.Final")
        implementation(project(":ports"))
        implementation("ch.qos.logback:logback-classic:$logbackVersion")
    }
}

configure(subprojects.filter { it.name != "ports" }) {
    dependencies {
        implementation("com.google.inject:guice:$guiceVersion")
    }
}



configure(subprojects.filter { it.name == "web" || it.name == "app" }) {
    repositories {
        maven { url = uri("https://dl.bintray.com/arrow-kt/arrow-kt/") }
        maven { url = uri("https://oss.jfrog.org/artifactory/oss-snapshot-local/") }
    }
    dependencies {
        kapt("io.arrow-kt:arrow-meta:$arrowVersion")
        implementation("io.arrow-kt:arrow-core:$arrowVersion")
        implementation("io.arrow-kt:arrow-syntax:$arrowVersion")
        implementation("io.ktor:ktor-server-netty:$ktorVersion")
        implementation("ch.qos.logback:logback-classic:$logbackVersion")
        implementation("io.ktor:ktor-server-core:$ktorVersion")
        implementation("io.ktor:ktor-server-host-common:$ktorVersion")
        implementation("io.ktor:ktor-auth:$ktorVersion")
        implementation("io.ktor:ktor-locations:$ktorVersion")
        implementation("io.ktor:ktor-auth-jwt:$ktorVersion")
        implementation("io.ktor:ktor-jackson:$ktorVersion")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
        testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    }
}

project("app") {
    repositories {
        maven { url = uri("https://kotlin.bintray.com/ktor") }
    }

    dependencies {
        implementation(project(":ports"))
        implementation(project(":domain"))
        implementation(project(":adapters:web"))
        implementation(project(":adapters:exposed"))
    }
}
project("domain") {
    dependencies {
        implementation(project(":ports"))
    }
}

configure(subprojects.filter { it.name == "web" }) {
    dependencies {
        compile("javax.xml.bind:jaxb-api:2.3.0")
    }
}


extra.apply {
    set("ktorVersion", ktorVersion)
    set("kotlinVersion", kotlinVersion)
    set("logbackVersion", logbackVersion)
    set("guiceVersion", guiceVersion)
}

repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
}

