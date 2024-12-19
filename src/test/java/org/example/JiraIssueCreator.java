package org.example;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class JiraIssueCreator {


    private static final String JIRA_BASE_URL = "https://mohamedjenad.atlassian.net/";
    private static final String API_TOKEN = "";
    private static final String USER_EMAIL = "sron4@hotmail.com";


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
                // Read response...
                System.out.println("API call successful.");
            } else {
                System.out.println("Failed to connect: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}