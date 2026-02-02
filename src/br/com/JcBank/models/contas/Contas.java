package br.com.JcBank.models.contas;

import br.com.JcBank.excecao.excecaoConta.ContaException;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public abstract class Contas {

    private int numero;
    private double saldo;
    Map<String, Double> histoticoMovimento = new HashMap<>();


    public Contas() {
        this.numero = numero;
    }

    public Contas(int numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
        String chave = (LocalDateTime.now() + "Abertura de conta: ") ;
        this.histoticoMovimento.put(chave, saldo);
    }

    public void sacar(double valor) {

        if(saldo >= valor) {
            saldo -= valor;
            String chave = (LocalDateTime.now() + "Sacado de conta: ") ;
            this.histoticoMovimento.put(chave, valor);
        }
        else {
            throw new ContaException("Saldo insuficiente");
        }
    }

    public void depositar(double valor) {
        if(valor <= 0){
            throw new ContaException("Não é possivel depositar esse valor");
        }
        saldo += valor;
        String chave = (LocalDateTime.now() + "Depositado na conta: ") ;
        this.histoticoMovimento.put(chave, valor);
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

            if (valorCheque <= 0){
                throw new ContaException("O valor do cheque não pode ser depositado");
            }

            saldo += valorCheque;

            String chave = (LocalDateTime.now() + "Deposito via cheque na conta: ") ;
            this.histoticoMovimento.put(chave, valorCheque);

        } catch (IOException | NumberFormatException e) {
        throw new RuntimeException("Erro ao processar cheque", e);
    }
    }


    public void extrato() {
        try (FileWriter fw = new FileWriter("extrato.txt")) {

            LocalDateTime data = LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");


            fw.write("Extrato \n");
            fw.write("Data: " + data.format(dtf)+ "\n\n");

            for (Map.Entry<String, Double> entry : this.histoticoMovimento.entrySet()) {
                fw.write(entry.getKey() + "R$: " + entry.getValue() + "\n");
            }

            fw.write("Saldo atual: R$ " + saldo);

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
