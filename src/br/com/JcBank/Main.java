package br.com.JcBank;

import br.com.JcBank.API.ApiVIaCep;
import br.com.JcBank.models.Empresa;
import br.com.JcBank.models.Endereco;
import br.com.JcBank.models.Pessoa;
import br.com.JcBank.models.contas.ContaCorrente;
import br.com.JcBank.models.contas.ContaEmpresa;
import br.com.JcBank.models.contas.Contas;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void menu(){
        System.out.println("========Menu========");
        System.out.println("1 - Cadastro de Contas");
        System.out.println("2 - Realizaar Operação");
        System.out.println("3 - Sair");
    }

    public static void menuCadastroConta(){
        System.out.println("========Cadastro de Contas========");
        System.out.println("1 - Cadastro de Conta para pessoa Fisica");
        System.out.println("2 - Cadastro de Conta para Empresa");
    }

    public static void menuRealizarOperacao( ){
        System.out.println("========Realizar Operação========");
        System.out.println("1 - Realizar Saque");
        System.out.println("2 - Realizar Deposito");
        System.out.println("3 - Depositar Cheque");
        System.out.println("4 - Tirar Extrato");
        System.out.println("5 - Realizar Compra com Cartão de Credito");
        System.out.println("6 - Realizar Emprestimo");
        System.out.println("7 - Sair");

    }

    public static ContaCorrente cadastrarPessoaFisica(Scanner sc){
        System.out.println("Digite seu nome: ");
        String nome = sc.nextLine();
        System.out.println("Digite sua data de nascimento: ");
        String dataNasc = sc.nextLine();
        System.out.println("Digite seu CPF: ");
        String cpf = sc.nextLine();
        System.out.println("Digite seu telefone: ");
        String telefone = sc.nextLine();
        System.out.println("Digite seu email: ");
        String email = sc.nextLine();
        System.out.println("Digite seu CEP");
        String cep = sc.nextLine();
        System.out.println("Digite o numero da sua residencia: ");
        int residencia = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite o numero do complemento: ");
        String comp = sc.nextLine();

        Endereco endereco = new Endereco();

        Pessoa pessoa = new Pessoa(nome,email,telefone,
                endereco.buscarEnderecoPorCep(cep,comp, residencia),
                dataNasc,cpf);

        System.out.println("Digite o saldo iniciar da sua conta: ");
        int saldo = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite o limite do seu cartão de credito: ");
        int limite = sc.nextInt();
        sc.nextLine();

        Random random = new Random();

        ContaCorrente c1 = new ContaCorrente(random.nextInt(10),saldo, limite, pessoa);

        return c1;
    }

    public static ContaEmpresa cadastrarContaEmpresa(Scanner sc){
        System.out.println("Digite o CNPJ");
        String cnpj = sc.nextLine();
        System.out.println("Digite o CEP");
        String cep2 = sc.nextLine();
        System.out.println("Digite o numero da residencia: ");
        int residencia2 = sc.nextInt();
        sc.nextLine();
        System.out.println("Digite o numero do complemento: ");
        String comp2 = sc.nextLine();

        Endereco endereco = new Endereco();
        Empresa empresa = new Empresa();
        Random random = new Random();

        Empresa em =  empresa.buscarDadosEmpresa(cnpj,
                endereco.buscarEnderecoPorCep(cep2, comp2, residencia2));

        System.out.println("Digite o saldo iniciar da sua conta: ");
        int saldo = sc.nextInt();
        sc.nextLine();

        ContaEmpresa contEmpresa = new ContaEmpresa(random.nextInt(10), saldo,em,30.000);

        return contEmpresa;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Endereco endereco = new Endereco();
        Empresa empresa = new Empresa();


        System.out.println("Bem vindo ao Banco JCBank");

        while(true){

            menu();
            int opcaoManu = sc.nextInt();


            switch (opcaoManu){
                case 1:
                    menuCadastroConta();
                    int opcaoCadastro = sc.nextInt();

                    switch (opcaoCadastro){
                        case 1:

                            ContaCorrente conta = cadastrarPessoaFisica(sc);

                            break;
                        case 2:
                            ContaEmpresa contaEmpresa = cadastrarContaEmpresa(sc);

                            break;
                    }

                    break;

                case 2:
                    menuRealizarOperacao();

                    break;
            }


        }
    }
}
