package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class JiraApiExample {

    private static final String JIRA_BASE_URL = "https://your-domain.atlassian.net";
    private static final String API_TOKEN = "your-api-token";
    private static final String USER_EMAIL = "your-email@example.com";

    public static void main(String[] args) {
        try {
            URL url = new URL(JIRA_BASE_URL + "/rest/api/2/project");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            String auth = USER_EMAIL + ":" + API_TOKEN;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            connection.setRequestProperty("Authorization", "Basic " + encodedAuth);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Print the response
                System.out.println("Projects: " + response.toString());
            } else {
                System.out.println("Failed to connect: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
