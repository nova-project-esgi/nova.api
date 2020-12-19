import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val msSqlVersion: String by project
val axonVersion: String by project
val hibernateValidatorVersion: String by project
val javaxValidationVersion: String by project
val javaJwtVersion: String by project
val jaxbApiVersion: String by project
val authSpringSecurityApiVersion: String by project

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    kotlin("jvm") version "1.4.10" apply false
    kotlin("plugin.spring") version "1.4.10" apply false
    id("io.spring.dependency-management") version "1.0.10.RELEASE" apply false
    id("org.springframework.boot") version "2.4.0" apply false
}

extra.apply {
    set("axonVersion", axonVersion)
    set("msSqlVersion", msSqlVersion)
    set("hibernateValidatorVersion", hibernateValidatorVersion)
    set("javaxValidationVersion", javaxValidationVersion)
    set("javaJwtVersion", javaJwtVersion)
    set("jaxbApiVersion", jaxbApiVersion)
    set("authSpringSecurityApiVersion", authSpringSecurityApiVersion)
}

allprojects {
    group = "com.esgi.nova"
    version = "0.0.1-SNAPSHOT"
//    configurations{
//        all{
//            exclude("org.springframework.boot" ,"spring-boot-starter-logging")
//        }
//    }


    tasks.withType<KotlinCompile> {
        println("Configuring KotlinCompile  $name in project ${project.name}...")
        kotlinOptions {
            languageVersion = "1.3"
            apiVersion = "1.3"
            jvmTarget = "11"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}

subprojects {

    repositories {
        mavenCentral()
    }

    apply(plugin = "io.spring.dependency-management")

    configure<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension> {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }
    }
}


//bootBuildImage{
//    builder=".."
//    imageName=tailender/imagedemo
//}




