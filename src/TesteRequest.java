
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 41583469
 */
public class TesteRequest {

    public static void main(String[] args) {
        System.out.println("Funcionando");
        try {
            URL url = new URL(
                    "http://api.olhovivo.sptrans.com.br/v2.1/Login/Autenticar?token=066dbb90dcc64c4d83cf656285e351dd0d4f69711310846cbce378defec153fc");
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.10", 3128));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
            //HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            //System.out.println(connection.getContentLength());
            connection.setRequestProperty("Accept", "*/*");
            System.out.println(String.valueOf(url).getBytes().length);

            //connection.addRequestProperty("Content-Length", "0");
            /**
             * You can't set the content length; it's done automatically. You can, however, use setFixedLengthStreamingMode(), when the content length is known in advance.
             */
            connection.setFixedLengthStreamingMode(0);
            connection.addRequestProperty("Content-Type", "application/json");

            connection.setUseCaches(true);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            

            //System.out.println(connection.getHeaderFields());
            System.out.println(connection.getHeaderField("Set-Cookie"));
            String cookie = connection.getHeaderField("Set-Cookie");
            System.out.println(connection.getResponseCode());
            System.out.println(connection.getResponseMessage());
            
            fazerBuscaLinha(cookie);
        } catch (MalformedURLException ex) {
            Logger.getLogger(TesteRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TesteRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static void fazerBuscaLinha(String cookie){
        try {
            URL url = new URL("http://api.olhovivo.sptrans.com.br/v2.1/Posicao?codigoLinha=507");
//            URL url = new URL("http://api.olhovivo.sptrans.com.br/v2.1//Linha/Buscar?termosBusca=Guarau");
            
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.10", 3128));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Cookie", cookie);
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
                bw.append(output);
            }
            bw.close();
            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(TesteRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TesteRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
