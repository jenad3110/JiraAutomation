package org.example;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class JiraCreateIssueExample {

    private static final String JIRA_BASE_URL = "https://your-domain.atlassian.net";
    private static final String API_TOKEN = "your-api-token";
    private static final String USER_EMAIL = "your-email@example.com";

    public static void main(String[] args) {
        try {
            URL url = new URL(JIRA_BASE_URL + "/rest/api/2/issue");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            String auth = USER_EMAIL + ":" + API_TOKEN;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            connection.setRequestProperty("Authorization", "Basic " + encodedAuth);

            connection.setDoOutput(true);

            String jsonInputString = "{"
                    + "\"fields\": {"
                    + "\"project\": {"
                    + "\"key\": \"PROJECT_KEY\""
                    + "},"
                    + "\"summary\": \"New issue from API\","
                    + "\"description\": \"Creating an issue via the Jira REST API\","
                    + "\"issuetype\": {"
                    + "\"name\": \"Task\""
                    + "}"
                    + "}"
                    + "}";

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Issue created successfully.");
            } else {
                System.out.println("Failed to create issue: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
