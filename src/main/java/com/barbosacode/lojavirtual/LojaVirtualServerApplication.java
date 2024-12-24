package com.barbosacode.lojavirtual;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "com.barbosacode.lojavirtual.models") // Informa onde estão as models
@EnableJpaRepositories(basePackages = {"com.barbosacode.lojavirtual.repositories"}) // Corrigido
@EnableTransactionManagement // Para gerenciar transações com o banco de dados
public class LojaVirtualServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaVirtualServerApplication.class, args);
	}

}
