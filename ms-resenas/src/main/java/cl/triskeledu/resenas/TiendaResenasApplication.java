package cl.triskeledu.resenas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TiendaResenasApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaResenasApplication.class, args);
	}

}
