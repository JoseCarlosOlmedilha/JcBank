package br.com.JcBank.models;

import br.com.JcBank.Dto.EmpresaDto;
import com.google.gson.Gson;

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

    public Empresa buscarCnae(String cnpj, Endereco endereco) {

        try {
            var url = "https://brasilapi.com.br/api/cnpj/v1/" + cnpj;

            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder(URI.create(url)).build();

            HttpResponse<String> json = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();

            EmpresaDto empresaDto = gson.fromJson(json.body(), EmpresaDto.class);

            Empresa empresa = new Empresa(empresaDto);

            empresa.endereco = endereco;

            return  empresa;


        }catch(Exception e){
            throw new RuntimeException("Erro ao buscar cnpj " + cnpj, e);
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
