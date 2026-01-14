package br.com.JcBank;

import br.com.JcBank.models.Empresa;
import br.com.JcBank.models.Endereco;
import br.com.JcBank.models.Pessoa;

public class Main {
    public static void main(String[] args) {

        Endereco endereco = new Endereco();



        Pessoa p1 = new Pessoa("Jose", "jose.olmedilha@hotmail.com", "34914026",
                endereco.buscarEnderecoPorCep("11700140","305",524)
                ,"24//11/2001", "35919307846");

        p1.comprovanteDeCadastroPessoa();

    }
}
