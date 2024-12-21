package jiraProject;

import Base.CommonAPI;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class JiraCreateIssueExample {

/*
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

            String jsonInputString =
                    "{"
                    + "\"fields\": {"
                    + "\"project\": {"
                    + "\"key\": \"" + PROJECT_KEY + "\"" // Project key is passed as an argument
                    + "},"

                            + "\"summary\":\""+ISSUE_TITLE+"\""

                    + "\"description\": \""+ISSUE_DESCRIPTION+"\","
                    + "\"issuetype\": {"
                    + "\"name\": \"Task\""
                    + "}"
                    + "}"
                    + "}";
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
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


 */

}
