// package com.example;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class WebConfig {

//     @Bean
//     public WebMvcConfigurer corsConfigurer() {
//         return new WebMvcConfigurer() {
//             @Override
//             public void addCorsMappings(CorsRegistry registry) {
//                 // CORS policy applies to all paths (/**)
//                 registry.addMapping("/**")
//                         // Allow only specific origins (e.g., your frontend at localhost:3000)
//                         .allowedOrigins("http://localhost:3000")
//                         // Allow specific HTTP methods (GET, POST, etc.)
//                         .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                         // Allow specific headers (or all headers using "*")
//                         .allowedHeaders("*")
//                         // Allow credentials like cookies, authentication headers, etc.
//                         .allowCredentials(true);
//             }
//         };
//     }
// }

