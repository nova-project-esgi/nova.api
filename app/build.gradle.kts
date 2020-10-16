plugins {
    application
    id("com.github.johnrengelman.shadow") version "4.0.3"
}
application {
    applicationDefaultJvmArgs = listOf("--add-opens", "java.base/java.lang=ALL-UNNAMED")
    mainClassName = "io.ktor.server.netty.EngineMain"
}


tasks {
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveBaseName.set("com.esgi.nova")
        mergeServiceFiles()
        manifest {
            attributes(mapOf("Main-Class" to "com.esgi.nova"))
        }
    }
}
//
//tasks {
//    build {
//        dependsOn(shadowJar)
//    }
//}
