package org.squad05.chatbot.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Configuração utilizada para permitir as solicitações web
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://squad05-portodigital.github.io", //Endereço front-end
                        "https://back-end-chatbot-deploy.up.railway.app",
                        "http://127.0.0.1:5500/") //Enderço do front de Ryan
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");

    }
}
