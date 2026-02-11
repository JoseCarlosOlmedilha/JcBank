package br.com.JcBank.models;

import br.com.JcBank.excecao.excecaoPessoa.ExcecaoPessoa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pessoa {

    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private Endereco endereco;
    private String telefone;
    private String email;

    public Pessoa(String nome, String email, String telefone, Endereco endereco, LocalDate dataNascimento, String cpf) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        boolean isCpfValido =  validarCpf(cpf);

        if (isCpfValido) {
            this.cpf = cpf;
        }
    }

    public boolean validarCpf(String cpf) {

            if (cpf == null || cpf.length() != 11) {
                throw new ExcecaoPessoa("CPF Nulo!");
            }

            List<Character> caracterCpf = new ArrayList<>();

            //Converter a String em uma array de char
            for (char caracter : cpf.toCharArray()) {
                caracterCpf.add(caracter);
            }

            int primeiroDigitoCpf = caracterCpf.get(9) - '0';
            int segundoDigitoCpf = caracterCpf.get(10) - '0';

            int soma = 0;
            int peso = 10;

            //interar o CPF ee calcular o valor total
            for (int i = 0; i < 9; i++) {
                int digitoCpf = caracterCpf.get(i) - '0';
                soma += digitoCpf * peso;
                peso--;
            }

            int validarPrimeiroDigito = (soma * 10) % 11;

            if (validarPrimeiroDigito == 10) {
                validarPrimeiroDigito = 0;
            }

            if (validarPrimeiroDigito != primeiroDigitoCpf) {
                throw new ExcecaoPessoa("Verifique o cpf digitado!");
            }

            int somaSegundoDigito = 0;
            int pesoSegundoDigito = 11;

            for (int i = 0; i < 10; i++) {
                int digitoCpf = caracterCpf.get(i) - '0';
                somaSegundoDigito += digitoCpf * pesoSegundoDigito;
                pesoSegundoDigito--;
            }

            int validarSegundoDigito = (somaSegundoDigito * 10) % 11;

            if (validarSegundoDigito == 10) {
                validarSegundoDigito = 0;
            }

            if (validarSegundoDigito != segundoDigitoCpf) {
                throw new ExcecaoPessoa("CPF invlido!");
            }
            return true;
    }

    public Pessoa() {}

    public void comprovanteDeCadastroPessoa()  {

        try {
            File file = new File("C:\\ComprovantePessoa.txt");

            FileWriter fw = new FileWriter(file);

            fw.write("Comprovante de cadastro");
            fw.write(toString());
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Erro ao criar o comprovante de cadastro de pessoas. " + e);
        }

    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco=" + endereco +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
