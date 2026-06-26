package cl.triskeledu.recomendaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TiendaRecomendacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaRecomendacionesApplication.class, args);
	}

}
