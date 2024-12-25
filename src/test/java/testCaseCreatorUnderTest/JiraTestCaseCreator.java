package testCaseCreatorUnderTest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

public class JiraTestCaseCreator {

    private static String PROJECT_KEY;
    private static String EMAIL;
    private static String API_TOKEN;
    private static String BASE_URL;

    static {
        try (InputStream input = JiraTestCaseCreator.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            } else {
                prop.load(input);
                PROJECT_KEY = prop.getProperty("jira.project.key");
                EMAIL = prop.getProperty("jira.email");
                API_TOKEN = prop.getProperty("jira.api.token");
                BASE_URL = prop.getProperty("jira.base.url");

                if (PROJECT_KEY == null || EMAIL == null || API_TOKEN == null || BASE_URL == null) {
                    System.out.println("Missing required properties in config.properties");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<TestCase> testCases = ExcelReader.readTestCases("file.xlsx");

        for (TestCase testCase : testCases) {
            createJiraTestCase(testCase);
        }
    }

    private static void createJiraTestCase(TestCase testCase) {
        try {
            JSONObject testCasePayload = new JSONObject();
            JSONObject fields = new JSONObject();

            fields.put("project", new JSONObject().put("key", PROJECT_KEY));
            fields.put("summary", testCase.getSummary());
            fields.put("issuetype", new JSONObject().put("name", "Test"));

           // fields.put("customfield_10046", new JSONArray().put(new JSONObject().put("value", testCase.getTestStatus())));
            fields.put("customfield_10043", testCase.getActualResults());
            fields.put("customfield_10044", testCase.getExpectedResults());
            fields.put("customfield_10045", testCase.getTestData());
            fields.put("customfield_10042", testCase.getTestSteps());

            testCasePayload.put("fields", fields);

            String payload = testCasePayload.toString();

            URL url = new URL(BASE_URL + "/rest/api/2/issue");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Basic " + encodeAuth(EMAIL, API_TOKEN));
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            InputStream responseStream = responseCode < HttpURLConnection.HTTP_BAD_REQUEST ? connection.getInputStream() : connection.getErrorStream();
            String responseBody = new String(responseStream.readAllBytes(), StandardCharsets.UTF_8);

            System.out.println("Response Code: " + responseCode);
            System.out.println("Response Body: " + responseBody);

            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Test case created successfully.");
            } else {
                System.out.println("Failed to create test case. HTTP error code: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String encodeAuth(String email, String apiToken) {
        String auth = email + ":" + apiToken;
        return Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
    }
}
