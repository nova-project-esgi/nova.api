package com.esgi.nova.web

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import java.util.ArrayList

import org.springframework.http.converter.HttpMessageConverter

import com.fasterxml.jackson.databind.ObjectMapper

import org.springframework.beans.factory.annotation.Autowired





@Configuration
open class WebConfig {
    @Bean
    open fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }


    @Bean
    open fun corsConfigurer(): WebMvcConfigurer? {
        return object : WebMvcConfigurerAdapter() {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .exposedHeaders(HttpHeaders.LOCATION)
                    .allowedHeaders(HttpHeaders.LOCATION)
                    .allowedMethods(*HttpMethod.values().map { it.toString() }.toTypedArray())
            }

            override fun configureContentNegotiation(configurer: ContentNegotiationConfigurer) {
                configurer.defaultContentType(MediaType.APPLICATION_JSON)
                    .mediaType("json", MediaType.APPLICATION_JSON)
//        //set path extension to true
//        configurer.favorPathExtension(true).
//                //set favor parameter to false
//        favorParameter(false).
//                //ignore the accept headers
//        ignoreAcceptHeader(true).
//                //dont use Java Activation Framework since we are manually specifying the mediatypes required below
//        useJaf(false).
//        defaultContentType(MediaType.APPLICATION_JSON).
//        mediaType("xml", MediaType.APPLICATION_XML).
//        mediaType("json", MediaType.APPLICATION_JSON);
            }
        }
    }
//    override fun addCorsMappings(registry: CorsRegistry) {
////        registry.addMapping("/**")
////            .allowedOrigins("http://localhost:4200")
////            .exposedHeaders(HttpHeaders.LOCATION)
////            .allowedHeaders(HttpHeaders.LOCATION)
////            .allowedMethods("POST", "GET", "PATCH", "DELETE", "PUT", "OPTION")
//    }
//    override fun configureContentNegotiation(configurer: ContentNegotiationConfigurer) {
//        configurer.defaultContentType(MediaType.APPLICATION_JSON)
//            .mediaType("json", MediaType.APPLICATION_JSON)
////        //set path extension to true
////        configurer.favorPathExtension(true).
////                //set favor parameter to false
////        favorParameter(false).
////                //ignore the accept headers
////        ignoreAcceptHeader(true).
////                //dont use Java Activation Framework since we are manually specifying the mediatypes required below
////        useJaf(false).
////        defaultContentType(MediaType.APPLICATION_JSON).
////        mediaType("xml", MediaType.APPLICATION_XML).
////        mediaType("json", MediaType.APPLICATION_JSON);
//    }
}