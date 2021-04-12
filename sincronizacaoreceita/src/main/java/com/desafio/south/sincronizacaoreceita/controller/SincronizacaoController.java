package com.desafio.south.sincronizacaoreceita.controller;
import com.desafio.south.sincronizacaoreceita.service.ReceitaService;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SincronizacaoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SincronizacaoController.class);

    public void processarArquivoReceita(String caminhoArquivo) {
        try {
            List<String[]> arquivoProcessado = getArquivoCsvProcessado(caminhoArquivo);
            Path pathArquivo = Paths.get(caminhoArquivo);
            String caminhoNovoArquivo = pathArquivo.getRoot() + "ArquivoReceitaProcessado.csv";
            Writer writer = Files.newBufferedWriter(Path.of(caminhoNovoArquivo));

            ICSVWriter csvWriter = new CSVWriterBuilder(writer)
                    .withSeparator(';')
                    .withQuoteChar(ICSVParser.NULL_CHARACTER)
                    .withEscapeChar(ICSVParser.DEFAULT_ESCAPE_CHARACTER)
                    .withLineEnd(ICSVWriter.DEFAULT_LINE_END)
                    .build();

            csvWriter.writeAll(arquivoProcessado);
            csvWriter.close();

        } catch (Exception e) {
            LOGGER.error("Erro ao processar arquivo receita. Arquivo: {}.", caminhoArquivo, e);
        }
    }

    private List<String[]> getArquivoCsvProcessado(String caminhoArquivo) throws IOException, CsvException {

        Reader reader = Files.newBufferedReader(Paths.get(caminhoArquivo));
        CSVParser parser = new CSVParserBuilder()
                               .withSeparator(';')
                               .withIgnoreQuotations(true)
                               .build();

        CSVReader csvReader = new CSVReaderBuilder(reader)
                                    .withSkipLines(0)
                                    .withCSVParser(parser)
                                    .build();

        List<String[]> arquivoCsv = csvReader.readAll();
        List<String[]> arquivoCsvProcessado = new ArrayList<>();
        String[] cabecalho = Arrays.copyOf(arquivoCsv.get(0), 5);

        cabecalho[4] = "resultado";
        arquivoCsvProcessado.add(cabecalho);
        arquivoCsv.remove(0);

        // Utilizado parallelStream para melhorar performance
        arquivoCsv.parallelStream().forEach(linhaArquivo -> {
            String[] novoArray = Arrays.copyOf(linhaArquivo, 5);
            // Adicionado coluna "resultado" ao CSV com valor P -> Processado com sucesso / E -> Erro no processamento
            novoArray[4] = processarRegistroCsv(novoArray) ? "P" : "E";
            arquivoCsvProcessado.add(novoArray);
        });

        return arquivoCsvProcessado;
    }

    private boolean processarRegistroCsv(String[] registroCsv) {
        ReceitaService receitaService = new ReceitaService();
        boolean processado = false;
        try {
            LOGGER.info("Processando conta: {}\n", registroCsv[1]);
            processado = receitaService.atualizarConta(registroCsv[0],
                                                       registroCsv[1].replace("-",""),
                                                       Double.parseDouble(registroCsv[2].replace(',', '.')),
                                                       registroCsv[3]);
        } catch (InterruptedException e) {
            LOGGER.error("Erro de interrupção na Thread durante processamento da conta: {}.", registroCsv[1], e);
        } catch (Exception e) {
            LOGGER.error("Erro geral de processamento na conta: {}.", registroCsv[1], e);
        }
        return processado;
    }

}
