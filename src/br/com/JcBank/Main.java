package br.com.JcBank;

import br.com.JcBank.API.ApiVIaCep;
import br.com.JcBank.excecao.excecaoCep.ExcecaoCep;
import br.com.JcBank.excecao.excecaoConta.ContaException;
import br.com.JcBank.excecao.excecaoPessoa.ExcecaoPessoa;
import br.com.JcBank.models.Empresa;
import br.com.JcBank.models.Endereco;
import br.com.JcBank.models.Pessoa;
import br.com.JcBank.models.contas.ContaCorrente;
import br.com.JcBank.models.contas.ContaEmpresa;
import br.com.JcBank.models.contas.Contas;

import java.io.File;
import java.util.*;

public class Main {

    public static void menu(){
        System.out.println("========Menu========");
        System.out.println("1 - Cadastro de Contas");
        System.out.println("2 - Realizaar Operação");
        System.out.println("3 - Sair");
        System.out.print("Digite a opção desejada: ");
    }

    public static void menuCadastroConta(){
        System.out.println("========Cadastro de Contas========");
        System.out.println("1 - Cadastro de Conta para pessoa Fisica");
        System.out.println("2 - Cadastro de Conta para Empresa");
        System.out.print("Digite a opção desejada: ");

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
        System.out.print("Digite a opção desejada: ");


    }

    /*public static ContaCorrente cadastrarPessoaFisica(Scanner sc){
        System.out.println("Digite seu nome: ");
        String nome = sc.nextLine();
        System.out.println("Digite sua data de nascimento: ");
        String dataNasc = sc.nextLine();

        while (true) {
            try {
                System.out.println("Digite seu CPF: ");
                String cpf = sc.nextLine().replaceAll("[^0-9]", "");

                new Pessoa().validarCpf(cpf);
                break;

            } catch (ExcecaoPessoa e) {
                System.out.println(e.getMessage());
            }
        //System.out.println("Digite seu CPF: ");
        //String cpf = sc.nextLine();

        System.out.println("Digite seu telefone: ");
        String telefone = sc.nextLine();
        System.out.println("Digite seu email: ");
        String email = sc.nextLine();

        While (true) {
            try {
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

        int numeroConta = random.nextInt(10);

        System.out.println("Numero da conta: " + numeroConta);

        ContaCorrente c1 = new ContaCorrente(numeroConta,saldo, limite, pessoa);

        return c1;
    }*/
    public static ContaCorrente cadastrarPessoaFisica(Scanner sc) {

        System.out.println("Digite seu nome: ");
        String nome = sc.nextLine();

        System.out.println("Digite sua data de nascimento: ");
        String dataNasc = sc.nextLine();

        // ========= CPF =========
        String cpf;
        while (true) {
            try {
                System.out.println("Digite seu CPF: ");
                cpf = sc.nextLine().replaceAll("[^0-9]", "");

                new Pessoa().validarCpf(cpf);
                break;

            } catch (ExcecaoPessoa e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Digite seu telefone: ");
        String telefone = sc.nextLine();

        System.out.println("Digite seu email: ");
        String email = sc.nextLine();

        // ========= ENDEREÇO =========
        Endereco endereco;
        while (true) {
            try {
                System.out.println("Digite seu CEP: ");
                String cep = sc.nextLine().replaceAll("[^0-9]", "");

                System.out.println("Digite o número da sua residência: ");
                int residencia = sc.nextInt();
                sc.nextLine();

                System.out.println("Digite o complemento: ");
                String complemento = sc.nextLine();

                endereco = new Endereco().buscarEnderecoPorCep(cep, complemento, residencia);
                break;

            } catch (ExcecaoCep e) {
                System.out.println(e.getMessage());
            }
        }

        // ========= PESSOA =========
        Pessoa pessoa = new Pessoa(nome, email, telefone, endereco, dataNasc, cpf);

        // ========= CONTA =========
        System.out.println("Digite o saldo inicial da sua conta: ");
        int saldo = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite o limite do seu cartão de crédito: ");
        int limite = sc.nextInt();
        sc.nextLine();

        Random random = new Random();
        int numeroConta = random.nextInt(900000) + 100000;

        System.out.println("Número da conta: " + numeroConta);

        return new ContaCorrente(numeroConta, saldo, limite, pessoa);
    }

  /*  public static ContaEmpresa cadastrarContaEmpresa(Scanner sc){
        System.out.print("Digite o CNPJ: ");
        String cnpj = sc.nextLine();

        System.out.print("Digite o CEP: ");
        String cep2 = sc.nextLine();

        System.out.print("Digite o número da empresa: ");
        int residencia2 = sc.nextInt();
        sc.nextLine();

        System.out.print("Digite o número do complemento: ");
        String comp2 = sc.nextLine();

        Endereco endereco = new Endereco();
        Empresa empresa = new Empresa();
        Random random = new Random();

        Empresa em =  empresa.buscarDadosEmpresa(cnpj,
                endereco.buscarEnderecoPorCep(cep2, comp2, residencia2));

        System.out.print("Digite o saldo iniciar da sua conta: ");
        int saldo = sc.nextInt();
        sc.nextLine();

        ContaEmpresa contEmpresa = new ContaEmpresa(random.nextInt(10), saldo,em,30.000);

        int numeroConta = contEmpresa.getNumero();

        System.out.print("Número da conta: " + numeroConta);

        return contEmpresa;
    }
*/

    public static ContaEmpresa cadastrarContaEmpresa(Scanner sc) {

        System.out.print("Digite o CNPJ: ");
        String cnpj = sc.nextLine();

        Endereco endereco = null;
        Random random = new Random();

        // ===== LOOP PARA CEP =====
        while (true) {
            try {
                System.out.print("Digite o CEP: ");
                String cep = sc.nextLine().replaceAll("[^0-9]", "");

                System.out.print("Digite o número da empresa: ");
                int numero = sc.nextInt();
                sc.nextLine();

                System.out.print("Digite o complemento: ");
                String complemento = sc.nextLine();

                endereco = new Endereco()
                        .buscarEnderecoPorCep(cep, complemento, numero);

                break; // só sai se o CEP for válido
            } catch (ExcecaoCep e) {
                System.out.println(e.getMessage());
            }
        }

        // ===== BUSCA DADOS DA EMPRESA =====
        Empresa empresa = new Empresa()
                .buscarDadosEmpresa(cnpj, endereco);

        // ===== DADOS DA CONTA =====
        System.out.print("Digite o saldo inicial da sua conta: ");
        int saldo = sc.nextInt();
        sc.nextLine();

        ContaEmpresa contaEmpresa = new ContaEmpresa(
                random.nextInt(1000),
                saldo,
                empresa,
                30000.00
        );

        System.out.println("Número da conta: " + contaEmpresa.getNumero());

        return contaEmpresa;
    }




    public static Contas buscarContas(List<Contas> contas, int numero){
        for (Contas conta : contas){
            if (conta.getNumero() == numero){
                return conta;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Contas> contas = new ArrayList<>();

        System.out.println("Bem vindo ao Banco JCBank");

        while(true){

            menu();
            int opcaoManu = sc.nextInt();


            switch (opcaoManu){
                case 1:
                    menuCadastroConta();
                    int opcaoCadastro = sc.nextInt();
                    sc.nextLine();

                    switch (opcaoCadastro){
                        case 1:
                            try {
                                ContaCorrente conta = cadastrarPessoaFisica(sc);
                                contas.add(conta);
                            }catch (ExcecaoPessoa e){
                                System.out.println("Houve um erro no cadastro, verifique. " + e.getMessage());
                            }

                            break;
                        case 2:
                            ContaEmpresa contaEmpresa = cadastrarContaEmpresa(sc);
                            contas.add(contaEmpresa);

                            break;
                    }

                    break;

                case 2:
                    menuRealizarOperacao();
                    int opcaoRealizarOperacao = sc.nextInt();

                    switch (opcaoRealizarOperacao){
                        case 1:
                        System.out.println("Digite o numero da sua conta: ");
                        int numConta = sc.nextInt();
                        sc.nextLine();

                        System.out.println("Qual valor deseja sacar: ");
                        double valorSacar = sc.nextInt();
                        sc.nextLine();

                        Contas c1 = buscarContas(contas, numConta);

                        if(c1 == null){
                            throw new ContaException("Esse numero de conta não existe.");
                        }

                        c1.sacar(valorSacar);

                        break;

                        case 2:
                            System.out.println("Digite o numero da sua conta: ");
                            int numContadeposito = sc.nextInt();
                            sc.nextLine();

                            System.out.println("Digite o valor a ser depositado: ");
                            double valorDepositado = sc.nextDouble();
                            sc.nextLine();

                            Contas c2  = buscarContas(contas, numContadeposito);

                            if(c2 == null){
                                throw new ContaException("Esse numero de conta não existe.");
                            }

                            c2.depositar(valorDepositado);

                            break;

                        case 3:
                            System.out.println("Digite o numero da sua conta: ");
                            int numConta2 = sc.nextInt();
                            sc.nextLine();

                            Contas c3 = buscarContas(contas, numConta2);

                            if(c3 == null){
                                throw new ContaException("Esse numero de conta não existe.");
                            }

                            File file = new File("ChequeDeposito.txt");

                            c3.depositarChaque(file);

                            break;

                        case 4:
                            System.out.println("Digite o numero da sua conta: ");
                            int numConta3 = sc.nextInt();
                            sc.nextLine();

                            Contas c4 = buscarContas(contas, numConta3);

                            if(c4 == null){
                                throw new ContaException("Esse numero de conta não existe.");
                            }

                            c4.extrato();

                            break;

                        case 5:
                            System.out.println("Digite o numero da sua conta: ");
                            int numConta4 = sc.nextInt();
                            sc.nextLine();

                            System.out.println("Digite o valor da sua compra: ");
                            double valorCompra = sc.nextDouble();
                            sc.nextLine();

                            Contas c5 = buscarContas(contas, numConta4);

                            if (c5 instanceof ContaCorrente) {
                                ContaCorrente cc = (ContaCorrente) c5;
                                cc.realizarCompraCartao(valorCompra);
                            }else {
                                System.out.println("Essa conta não tem permissão para compra no cartão");
                            }

                            break;
                        case 6:
                            System.out.println("Digite o numero da sua conta: ");
                            int numConta5 = sc.nextInt();
                            sc.nextLine();

                            System.out.println("Digite o valor do seu emprestimo: ");
                            double valorEmprestimo = sc.nextDouble();
                            sc.nextLine();

                            Contas c6 = buscarContas(contas, numConta5);

                            if (c6 instanceof ContaEmpresa) {
                                ContaEmpresa ce = (ContaEmpresa) c6;

                                ce.setValorParaEmprestimo(valorEmprestimo);
                            }

                            break;


                        case 7:
                            System.out.println("Saindo");
                            break;
                    }

                    break;
                case 3:
                    //sair
                    System.out.println("Encerrando o sistema");
                    return;
            }
        }
    }
}
