package br.com.JcBank.models;

import br.com.JcBank.Dto.EnderecoDto;
import br.com.JcBank.excecao.excecaoCep.ExcecaoCep;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Endereco {

    private String cep;
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;

    public Endereco() {
    }

    public Endereco(String cep, String logradouro, int numero,
                    String complemento, String bairro, String cidade,
                    String estado, String pais) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Endereco(EnderecoDto enderecoDto) {
        this.cep = enderecoDto.cep();
        this.logradouro = enderecoDto.logradouro();
        this.bairro = enderecoDto.bairro();
        this.cidade = enderecoDto.localidade();
        this.estado = enderecoDto.estado();
    }

    public Endereco buscarEnderecoPorCep(String cep, String complemento, int numero) {

        try {

            if(cep.length() != 8){
                throw new ExcecaoCep("Verifique o Cep Digitado");
            }

            String url = "https://viacep.com.br/ws/" + cep + "/json/";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create(url)).build();
            HttpResponse<String> json = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();

            EnderecoDto enderecoDto = gson.fromJson(json.body(), EnderecoDto.class);

            Endereco endereco = new Endereco(enderecoDto);

            endereco.setComplemento(complemento);
            endereco.setNumero(numero);

            return endereco;

        } catch (InterruptedException | IOException e) {
            System.out.println("Erro ao consultar API do cep");
            System.out.println(e.getMessage());
            return null;
        }catch (ExcecaoCep e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero=" + numero +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }
}
