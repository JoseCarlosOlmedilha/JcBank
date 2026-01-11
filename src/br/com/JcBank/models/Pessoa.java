package br.com.JcBank.models;

import br.com.JcBank.excecao.excecaoPessoa.ExcecaoPessoa;

import java.util.ArrayList;
import java.util.List;

public class Pessoa {

    private String nome;
    private String dataNascimento;
    private String cpf;
    private Endereco endereco;
    private String telefone;
    private String email;

    public Pessoa(String nome, String email, String telefone, Endereco endereco, String dataNascimento, String cpf) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }

    public boolean validarCpf(String cpf) {

        try {
            if (cpf == null || cpf.length() != 11) {
                throw new ExcecaoPessoa("CPF inválido!");
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
                throw new ExcecaoPessoa("CPF inválido!");
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
        } catch (ExcecaoPessoa e) {
            System.out.println(e.getMessage());
        }

        return false;

    }

    public Pessoa() {}

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
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
