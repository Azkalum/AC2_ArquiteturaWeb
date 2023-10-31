package github.com.azkalum;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Software System XYZ LTDA",
                version = "1.0",
                description = "API para gerenciar agendamentos"
        )
)
public class Ac2Application {

    public static void main(String[] args) {
        SpringApplication.run(Ac2Application.class, args);
    }

}
