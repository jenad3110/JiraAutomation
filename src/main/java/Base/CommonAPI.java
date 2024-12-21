package Base;

import Base.ConfigReader;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CommonAPI {


    public static String JIRA_BASE_URL;
    public static String API_TOKEN;
    public static String USER_EMAIL;
    public static String PROJECT_KEY;


    public static void retrieveCredentials(){
        ConfigReader config = new ConfigReader("configPerso.properties");// use "config.properties"
        JIRA_BASE_URL = config.getBaseUrl();
        API_TOKEN = config.getApiToken();
        USER_EMAIL = config.getEmail();
        PROJECT_KEY = config.getProjectKey();

    }





}
