package com.esgi.nova

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

//@ComponentScan("com.esgi.nova.query")
@SpringBootApplication
open class ApiApplication {

}
//@Configuration
//@ComponentScan
//@Suppress("UnusedPrivateClass")
//private class MainConfig

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}
