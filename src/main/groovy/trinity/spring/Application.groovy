package trinity.spring

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "trinity")
class Application {

    static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(Application.class, args)
    }

}
