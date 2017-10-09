package br.com.panelinha.parser;

import br.com.panelinha.model.Onibus;
import br.com.panelinha.service.ServiceConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;



public class OnibusParser {

    public static String openURL(String place) {

        String return_str = "";
        try {

            HttpURLConnection conn = ServiceConnection.doConnection(place);

            int code = conn.getResponseCode();

            if (code == 407) {
                System.out.println("Falha na autenticação do proxy");
            } else if (code == 200) {
                System.out.println("Sucesso");
                String output;
                StringBuilder resp = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                while ((output = br.readLine()) != null) {
                    resp.append(output);
                }
                br.close();

                return_str = resp.toString();
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(OnibusParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OnibusParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return return_str;
    }

    
    public Onibus parse(String content) {
        Onibus r = null;
        JsonObject root;
        try (JsonReader reader = Json.createReader(new StringReader(content))) {
            root = reader.readObject();
            reader.close();
        }

        JsonArray restaurants = root.getJsonArray("restaurants");

        List<Onibus> restList = new ArrayList<>();

        for (int i = 0; i < restaurants.size(); i++) {

            JsonObject rest = restaurants.getJsonObject(i);

            JsonObject restaurant = rest.getJsonObject("restaurant");
            String name = restaurant.getJsonString("name").toString();
            String url = restaurant.getJsonString("url").toString();
            String image = restaurant.getJsonString("thumb").toString();
//            r = new Onibus(name, image, url);

            JsonObject location = restaurant.getJsonObject("location");
            String address = location.getJsonString("address").getString();
            String city = location.getJsonString("city").getString();
//            r.setLocation(new Location(address, city));
            restList.add(r);

        }
//        return restList;
        return null;
    }
}
