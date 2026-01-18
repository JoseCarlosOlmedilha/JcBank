package br.com.JcBank.models.contas;

import br.com.JcBank.excecao.excecaoConta.ContaException;
import br.com.JcBank.models.Pessoa;

public class ContaCorrente extends Contas{
    private Pessoa pessoa;
    private double limite;
    private double faturaCartao;

    public ContaCorrente(int numero, double saldo, double limite, Pessoa pessoa) {
        super(numero, saldo);
        this.pessoa = pessoa;
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getFaturaCartão() {
        return faturaCartao;
    }

    public void realizarCompraCartao(double valor){
        if((faturaCartao + valor) > limite){
            throw new ContaException("O valor da compra excede o limite do cartão");
        }

        faturaCartao += valor;

    }


}
