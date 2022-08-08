package com.trybe.conversorcsv;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class TrataEntradas {
  private String nome;
  private String dataNascimento;
  private String cpf;

  /** Trata entradas constructor. */
  public TrataEntradas(String nome, String dataNascimento, String cpf) {
    this.nome = nome;
    this.dataNascimento = dataNascimento;
    this.cpf = cpf;
  }

  /** Nome getter. */
  public String getNome() {
    return nome.toUpperCase();
  }

  /** Data de nascimento getter. */
  public String getDataNascimento() {
    String[] data = dataNascimento.split("/");

    return data[2] + "-" + data[1] + "-" + data[0];
  }

  /** Cpf getter. */
  public String getCpf() {
    ArrayList<String> cpfSeparado = new ArrayList<String>(
            Arrays.asList(cpf.split("")));

    cpfSeparado.add(9, "-");
    cpfSeparado.add(6, ".");
    cpfSeparado.add(3, ".");

    return String.join("", cpfSeparado);
  }
}
