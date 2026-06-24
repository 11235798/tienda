package cl.triskeledu.descuentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = "cl.triskeledu.catalogo.client")
@SpringBootApplication
public class TiendaDescuentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaDescuentosApplication.class, args);
	}

}
