package br.com.JcBank.API;

import br.com.JcBank.Dto.EmpresaDto;
import br.com.JcBank.Dto.EnderecoDto;
import br.com.JcBank.excecao.excecaoCep.ExcecaoCep;
import br.com.JcBank.models.Empresa;
import br.com.JcBank.models.Endereco;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiVIaCep {

    public Endereco conexaoApiViaCep(String cep){

        try {


            if(cep.length() != 8){
                throw new ExcecaoCep("\n Verifique o Cep Digitado");
            }


            String url = "https://viacep.com.br/ws/" + cep + "/json/";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create(url)).build();
            HttpResponse<String> json = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();

            EnderecoDto enderecoDto = gson.fromJson(json.body(), EnderecoDto.class);

            Endereco endereco = new Endereco(enderecoDto);

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



}
