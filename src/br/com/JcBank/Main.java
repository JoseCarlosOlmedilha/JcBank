package br.com.JcBank;

import br.com.JcBank.models.Empresa;
import br.com.JcBank.models.Endereco;
import br.com.JcBank.models.Pessoa;

public class Main {
    public static void main(String[] args) {

        Empresa emp = new Empresa();

        Endereco endereco = new Endereco();

        Endereco end = endereco.buscarEnderecoPorCep("11050201", "Loja", 21);

        Empresa empresa = emp.buscarCnae("03069071000139", end);

        System.out.println(empresa);



    }
}
