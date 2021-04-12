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

SincronizacaoReceita.jar -> <br> Encontrado em: https://github.com/edanker-philips/sistema-receita/blob/main/SincronizacaoReceita.jar

ArquivoReceita.csv -> <br> Entrada de exemplo utilizada nos testes encontrado em: https://github.com/edanker-philips/sistema-receita/blob/main/ArquivoReceita.csv
Obs.: O nome do arquivo e quantidade de registros pode ser alterado, apenas usado como exemplo neste caso.

Conteúdo arquivo CSV de exemplo utilizado: 

agencia;conta;saldo;status <br>
0101;12225-6;100,00;A <br>
0101;12226-8;3200,50;A <br>
3202;40011-1;-35,12;I <br>
3202;54001-2;0,00;P <br>
3202;00321-2;34500,00;B <br>

Arquivo de saída gerado no mesmo diretório do arquivo de entrada informado com nome: "ArquivoReceitaProcessado.csv"
Adicionado coluna "resultado" ao CSV com valor P -> Processado com sucesso / E -> Erro no processamento
