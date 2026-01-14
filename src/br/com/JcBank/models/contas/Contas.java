package br.com.JcBank.models.contas;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public abstract class Contas {

    private int numero;
    private double saldo;

    public Contas() {
        this.numero = numero;
    }

    public Contas(int numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public void sacar(double valor) {
        saldo -= valor;
        System.out.println("Saldo atual: " + saldo);
    }

    public void depositar(double valor) {
        saldo += valor;
        System.out.println("Saldo atual: " + saldo);
    }

    public void depositarChaque(File arquivo){

        try (FileReader fr = new FileReader(arquivo)){

            StringBuilder conteudo = new StringBuilder();
            int data;

            while ((data = fr.read()) != -1) {
                conteudo.append((char) data);
            }

            String texto = conteudo.toString();
            int inicio = texto.indexOf("{");
            int fim = texto.indexOf("}");

            if (inicio == -1 || fim == -1 || fim <= inicio) {
                throw new RuntimeException("Valor do cheque inválido");
            }

            String valorStr = texto.substring(inicio + 1, fim);
            double valorCheque = Double.parseDouble(valorStr);

            // Soma ao saldo
            saldo += valorCheque;

        } catch (IOException | NumberFormatException e) {
        throw new RuntimeException("Erro ao processar cheque", e);
    }
    }


    public void extrato() {
        try (FileWriter fw = new FileWriter("extrato.txt")) {

            LocalDateTime data = LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");


            fw.write("Extrato\n");
            fw.write(data.format(dtf));
            fw.write(this.toString());

        } catch (IOException e) {
            throw new RuntimeException("Erro ao gerar extrato", e);
        }
    }

    public int getNumero() {
        return numero;
    }


    @Override
    public String toString() {
        return "Contas{ " +
                "numero= " + numero +
                ", saldo= " + saldo +
                " }";
    }

}
