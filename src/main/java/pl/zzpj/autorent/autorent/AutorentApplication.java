package pl.zzpj.autorent.autorent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AutorentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutorentApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("http://localhost:8080/cars").allowedOrigins("http://localhost:8080");
                registry.addMapping("http://localhost:8080/cars{id}").allowedOrigins("http://localhost:8080");
                registry.addMapping("http://localhost:8080/offers").allowedOrigins("http://localhost:8080");
                registry.addMapping("http://localhost:8080/offers/{id}").allowedOrigins("http://localhost:8080");
            }
        };
    }

}
