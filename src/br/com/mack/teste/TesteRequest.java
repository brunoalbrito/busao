package br.com.mack.teste;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TesteRequest {

    public static void main() {
        System.out.println("Funcionando");
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
            System.out.println(String.valueOf(url).getBytes().length);

            //connection.addRequestProperty("Content-Length", "0");
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

            //System.out.println(connection.getHeaderFields());
            System.out.println(connection.getHeaderField("Set-Cookie"));
            List<String> cookies = connection.getHeaderFields().get("Set-Cookie");

            System.out.println(connection.getResponseCode());
            System.out.println(connection.getResponseMessage());

            fazerBuscaLinha(cookies);
        } catch (MalformedURLException ex) {
            Logger.getLogger(TesteRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TesteRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void fazerBuscaLinha(List<String> cookies) {
        try {
//            URL url = new URL("http://api.olhovivo.sptrans.com.br/v2.1/Linha/Buscar?termosBusca=8000");
            URL url = new URL("http://api.olhovivo.sptrans.com.br/v2.1/Posicao/Linha?codigoLinha=33275");
//            URL url = new URL("http://api.olhovivo.sptrans.com.br/v2.1//Linha/Buscar?termosBusca=Guarau");

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

            System.out.println("\n\n\n\n*************Resultado da busca***************");
            System.out.println(connection.getResponseCode());
            System.out.println(connection.getResponseMessage());
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String output = null;
            File file = new File("saida.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            while ((output = br.readLine()) != null) {
                System.out.println(output.replace("\"hr\":", "\n"));
                bw.write(output);
                bw.newLine();
            }
            bw.close();

        } catch (MalformedURLException ex) {
            Logger.getLogger(TesteRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TesteRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
