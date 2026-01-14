package br.com.JcBank.API;

import br.com.JcBank.Dto.EmpresaDto;
import br.com.JcBank.models.Empresa;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiBrasilApi {

    public Empresa conexaoApiBrasilApi(String cnpj){

        try {
            var url = "https://brasilapi.com.br/api/cnpj/v1/" + cnpj;

            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder(URI.create(url)).build();

            HttpResponse<String> json = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();

            EmpresaDto empresaDto = gson.fromJson(json.body(), EmpresaDto.class);

            Empresa empresa = new Empresa(empresaDto);

            return  empresa;


        }catch(Exception e){
            throw new RuntimeException("Erro ao buscar cnpj " + cnpj, e);
        }

    }


}
