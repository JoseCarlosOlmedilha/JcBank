package br.com.JcBank.models.contas;

import br.com.JcBank.models.Empresa;

public class ContaEmpresa extends Contas{
    private Empresa empresa;
    private double valorParaEmprestimo;

    public ContaEmpresa(int numero, double saldo, Empresa empresa, double valorParaEmprestimo) {
        super(numero, saldo);
        this.empresa = empresa;
        this.valorParaEmprestimo = valorParaEmprestimo;
    }

    public void realizarEmprestimo(){

    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public double getValorParaEmprestimo() {
        return valorParaEmprestimo;
    }

    public void setValorParaEmprestimo(double valorParaEmprestimo) {
        this.valorParaEmprestimo = valorParaEmprestimo;
    }


}
