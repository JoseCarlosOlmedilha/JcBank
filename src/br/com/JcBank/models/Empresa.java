package br.com.JcBank.models;

import br.com.JcBank.API.ApiBrasilApi;
import br.com.JcBank.Dto.EmpresaDto;
import br.com.JcBank.excecao.excecaoCnpj.ExcecaoCnpj;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Empresa {

    private String cnpj;
    private String nome;
    private Endereco endereco;
    private int codCnae;
    private String descricaoCnae;

    public Empresa(){

    }

    public Empresa(String cnpj, Endereco endereco, String nome, int codCnae, String descricaoCnae) {
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.descricaoCnae = descricaoCnae;
        this.nome = nome;
        this.codCnae = codCnae;
    }

    public Empresa(EmpresaDto empresaDto) {
        this.cnpj = empresaDto.cnpj();
        this.nome = empresaDto.razao_social();
        this.codCnae = empresaDto.cnae_fiscal();
        this.descricaoCnae = empresaDto.cnae_fiscal_descricao();
    }

    public Empresa buscarDadosEmpresa(String cnpj, Endereco endereco) {

        ApiBrasilApi apiBrasilApi = new ApiBrasilApi();

        Empresa empresa = apiBrasilApi.conexaoApiBrasilApi(cnpj);
        if (empresa == null) {
            throw new ExcecaoCnpj("Cnpj invalido, favor verificar");
        }

        empresa.endereco = endereco;

        return empresa;
    }

    public void comprovanteDeCadastroEmpresa() {

        try {
            File file = new File("C:\\ComprovanteEmpresa.txt");

            FileWriter fw = new FileWriter(file);

            fw.write("Comprovante de cadastro ");
            fw.write(toString());
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Erro ao criar o comprovante de cadastro da empresa. " + e);
        }

    }

    public String getCnpj() {
        return cnpj;
    }

    public int getCodCnae() {
        return codCnae;
    }

    public void setCodCnae(int codCnae) {
        this.codCnae = codCnae;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "cnpj=" + cnpj +
                ", nome='" + nome + '\'' +
                ", endereco=" + endereco +
                ", codCnae=" + codCnae +
                ", descricaoCnae='" + descricaoCnae + '\'' +
                '}';
    }
}
