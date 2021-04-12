# sistema-receita

Sistema para processamento de contas

Funcionalidade exigidas:

Criar uma aplicação SprintBoot standalone. Exemplo: java -jar SincronizacaoReceita
Processa um arquivo CSV de entrada com o formato abaixo.
Envia a atualização para a Receita através do serviço (SIMULADO pela classe ReceitaService).
Retorna um arquivo com o resultado do envio da atualização da Receita. Mesmo formato adicionando o resultado em uma nova coluna.


Tecnologias utilizadas:

InteliJJ IDEA, Java JDK 11, Maven, OpenCSV, SpringBoot

Exemplo de utilização/execução:

java -jar SincronizacaoReceita.jar "C://ArquivoReceita.csv"

SincronizacaoReceita.jar -> Encontrado em: https://github.com/edanker-philips/sistema-receita/blob/main/SincronizacaoReceita.jar

C://ArquivoReceita.csv:

Arquivo CSV de exemplo: 

agencia;conta;saldo;status
0101;12225-6;100,00;A
0101;12226-8;3200,50;A
3202;40011-1;-35,12;I
3202;54001-2;0,00;P
3202;00321-2;34500,00;B







