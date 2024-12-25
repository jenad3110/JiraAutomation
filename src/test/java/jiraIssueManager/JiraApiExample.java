package jiraIssueManager;

import Base.CommonAPI;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class JiraApiExample extends CommonAPI {


    public static void main(String[] args) {

        retrieveCredentials();
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
