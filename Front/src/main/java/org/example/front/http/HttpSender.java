package org.example.front.http;
import org.example.front.RSA.Key;
import org.example.front.model.Person;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpSender {
    private static final String REGISTER = "http://localhost:8082/register";

    private static final String LOGIN = "http://localhost:8082/login";

    private static final String ENDPOINT = "http://localhost:8082/getPublicKey/username=";

    public static boolean PostRegister(Person person) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            String json = String.format(
                    "{\"name\":\"%s\",\"password\":\"%s\",\"public_key\":\"%s\"}",
                    person.getName(), person.getPassword(), person.getPublic_key()
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(java.net.URI.create(REGISTER))
                    .header("Content-Type", "application/json") // <- dodane
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return response.statusCode() == 200;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public static boolean PostLogin(Person person) {

        try {
            HttpClient client = HttpClient.newHttpClient();

            String json = String.format(
                    "{\"name\":\"%s\",\"password\":\"%s\"}",
                    person.getName(), person.getPassword()
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(java.net.URI.create(LOGIN))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return response.statusCode() == 200;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }






    public static Key GetKey(String username) {
        try {
            HttpClient client = HttpClient.newHttpClient();


            HttpRequest request = HttpRequest.newBuilder()
                    .uri(java.net.URI.create(ENDPOINT + username))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }

}