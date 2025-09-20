package com.conexxa.grupo_estudos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrupoEstudosApplication {

	public static void main(String[] args) {
		// --- ADICIONE ESTA LINHA PARA DIAGNÓSTICO ---
		System.out.println("--- LENDO A VARIAVEL DE AMBIENTE DATABASE_URL: " + System.getenv("DATABASE_URL") + " ---");

		SpringApplication.run(GrupoEstudosApplication.class, args);
	}

}