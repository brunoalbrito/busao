package br.com.panelinha.parser;

import br.com.mack.teste.TesteRequest;
import br.com.panelinha.model.Linha;
import br.com.panelinha.model.Onibus;
import br.com.panelinha.service.ServiceConnection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;

/**
 *
 * @author Bruno
 */
public class LinhaParser {

    public static List<Linha> buscarLinhas(String codigoLinha) {
        System.out.println("Funcionando");

        List<String> cookies = null;
        try {
            URL url = new URL(
                    "http://api.olhovivo.sptrans.com.br/v2.1/Login/Autenticar?token=066dbb90dcc64c4d83cf656285e351dd0d4f69711310846cbce378defec153fc");
//            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.10", 3128));
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            //System.out.println(connection.getContentLength());
            connection.setRequestProperty("Accept", "*/*");

            /**
             * You can't set the content length; it's done automatically. You
             * can, however, use setFixedLengthStreamingMode(), when the content
             * length is known in advance.
             */
            connection.setFixedLengthStreamingMode(0);
            connection.addRequestProperty("Content-Type", "application/json");
            connection.setUseCaches(true);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            cookies = connection.getHeaderFields().get("Set-Cookie");

            System.out.println(connection.getResponseCode());

        } catch (MalformedURLException ex) {
            Logger.getLogger(TesteRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TesteRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fazerBuscaLinha(cookies, codigoLinha);
    }

    public static List<Linha> fazerBuscaLinha(List<String> cookies, String codigoLinha) {

        List<Linha> linhas = null;

        try {

            URL url = new URL("http://api.olhovivo.sptrans.com.br/v2.1//Linha/Buscar?termosBusca=" + codigoLinha);

//            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.10", 3128));
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            for (String cookie : cookies) {
                connection.addRequestProperty("Cookie", cookie.split(";", 2)[0]);
            }
            connection.setUseCaches(true);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            System.out.println("*************Resultado da busca***************");
            System.out.println(connection.getResponseCode());

            int code = connection.getResponseCode();

            if (code == 407) {
                System.out.println("Falha na autenticação do proxy");
            } else if (code == 200) {
                System.out.println("Sucesso");
                String output;
                StringBuilder resp = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((output = br.readLine()) != null) {
                    resp.append(output);
                }
                br.close();

                String return_str = resp.toString();

                linhas = parseLinhas(return_str);
            }

            File file = new File("saida.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.close();

        } catch (MalformedURLException ex) {
            Logger.getLogger(TesteRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TesteRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

        return linhas;
    }

    public static List<Linha> parseLinhas(String content) {
        List<Linha> linhas = new ArrayList<>();

        Linha linha = null;

        JsonArray root = null;

        JsonReader jsonReader = Json.createReader(new StringReader(content));
        root = jsonReader.readArray();
//        
//        System.out.println(root.toString());

        
        for (int i = 0; i < root.size(); i++) {
            JsonObject jo = root.getJsonObject(i);
            linha = new Linha();
            linha.setCodigoLinha(jo.getInt("cl"));
            linha.setPrimeiroLetreiro(jo.getString("lt"));
            linha.setSegundoLetreiro(jo.getInt("tl"));
            linha.setSentidoOperacional(jo.getInt("sl"));
            linha.setTerminalPrincipal(jo.getString("tp"));
            linha.setTerminalSegundario(jo.getString("ts"));
            linhas.add(linha);
        }

        return linhas;
    }
}
