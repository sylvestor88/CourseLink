package courseLink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = { "courseLink","com.project.implementation","com.project.dto","com.project.util" })
@EnableScheduling
public class Application {
	
    public static void main(String[] args) {
    	
        SpringApplication.run(Application.class);
    }
}
