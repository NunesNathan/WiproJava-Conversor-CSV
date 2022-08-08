package com.trybe.conversorcsv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Conversor {

  /**
   * Função utilizada apenas para validação da solução do desafio.
   *
   * @param args Não utilizado.
   * @throws IOException Caso ocorra algum problema ao ler os arquivos de entrada ou
   *                     gravar os arquivos de saída.
   */
  public static void main(String[] args) throws IOException {
    File pastaDeEntradas = new File("./entradas/");
    File pastaDeSaidas = new File("./saidas/");
    new Conversor().converterPasta(pastaDeEntradas, pastaDeSaidas);
  }

  /**
   * Converte todos os arquivos CSV da pasta de entradas. Os resultados são gerados
   * na pasta de saídas, deixando os arquivos originais inalterados.
   *
   * @param pastaDeEntradas Pasta contendo os arquivos CSV gerados pela página web.
   * @param pastaDeSaidas Pasta em que serão colocados os arquivos gerados no formato
   *                      requerido pelo subsistema.
   *
   * @throws IOException Caso ocorra algum problema ao ler os arquivos de entrada ou
   *                     gravar os arquivos de saída.
   */
  public void converterPasta(File pastaDeEntradas, File pastaDeSaidas) throws IOException {
    File[] arquivos = pastaDeEntradas.listFiles();

    if (!pastaDeSaidas.exists()) {
      pastaDeSaidas.mkdirs();
    }

    for (int index = 0; index < arquivos.length; index += 1) {
      FileReader fr;
      BufferedReader br;
      FileWriter fw;
      BufferedWriter bw;

      File arquivo = arquivos[index];

      String nomeDoArquivoAtual = arquivo.getName();

      File lidoAtual = new File(pastaDeEntradas + "/" + nomeDoArquivoAtual);
      File escritoAtual = new File(pastaDeSaidas + "/" + nomeDoArquivoAtual);

      if (!escritoAtual.exists()) {
        escritoAtual.createNewFile();
      }

      try {
        fr = new FileReader(lidoAtual);
        br = new BufferedReader(fr);
        fw = new FileWriter(escritoAtual);
        bw = new BufferedWriter(fw);

        String header = br.readLine();
        bw.write(header);
        bw.flush();

        String linha;

        do {
          bw.newLine();
          linha = br.readLine();
          if (linha != null) {
            String[] linhaSeparada = linha.split(",");

            TrataEntradas te = new TrataEntradas(
                    linhaSeparada[0], linhaSeparada[1], linhaSeparada[3]);

            String linhaBuffered = te.getNome()
                    + "," + te.getDataNascimento()
                    + "," + linhaSeparada[2]
                    + "," + te.getCpf();

            bw.write(linhaBuffered);
            bw.flush();
          }
        } while (linha != null);

        fr.close();
        br.close();
        fw.close();
        bw.close();
      } catch (IOException e) {
        e.printStackTrace();
      }

    }

  }
}