package io.satori.demo;

import io.satori.demo.schedulerdemo.ScheduledTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

@EnableScheduling
@EnableJpaRepositories("io.satori.demo.dao")
@EntityScan("io.satori.demo.entity")
@SpringBootApplication
public class MvcDemoApplication {

    @Autowired
    ScheduledTasks scheduledTasks;

    public static void main(String[] args) {
        SpringApplication.run(MvcDemoApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/books").allowedOrigins("http://localhost:8080");
            }
        };

    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");
            //scheduledTasks.scheduleTaskWithFixedDelay();
            //scheduledTasks.scheduleTaskWithFixedRate();
            scheduledTasks.scheduleTaskWithCronExpression();

            /*String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }*/
        };
    }
}
