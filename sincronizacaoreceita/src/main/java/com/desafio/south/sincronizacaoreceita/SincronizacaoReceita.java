package com.desafio.south.sincronizacaoreceita;

import com.desafio.south.sincronizacaoreceita.controller.SincronizacaoController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SincronizacaoReceita {

	public static void main(String[] args) {
		SpringApplication.run(SincronizacaoReceita.class, args);
		// Exemplo como chamar o "serviço" do Banco Central.
		// ReceitaService receitaService = new ReceitaService();
		// receitaService.atualizarConta("0101", "123456", 100.50, "A");
		//C:\ArquivoReceita.csv

		SincronizacaoController sync = new SincronizacaoController();
		//sync.gerarArquivoReceita(args[0]);

		long tempoInicial = System.currentTimeMillis();
		System.out.println("Inicio processamento arquivo receita...");
		//sync.processarArquivoReceita("C://ArquivoReceita.csv");
		sync.processarArquivoReceita(args[0]);
		long tempoFinal = System.currentTimeMillis();
		System.out.printf("Fim processamento arquivo receita: %.3f ms%n", (tempoFinal - tempoInicial) / 1000d);
	}

}
