package Base;

public class CommonAPI {


    public static String JIRA_BASE_URL;
    public static String API_TOKEN;
    public static String USER_EMAIL;
    public static String PROJECT_KEY;


    public static void retrieveCredentials(){
        ConfigReader config = new ConfigReader("config.properties");// use "config.properties"
        JIRA_BASE_URL = config.getBaseUrl();
        API_TOKEN = config.getApiToken();
        USER_EMAIL = config.getEmail();
        PROJECT_KEY = config.getProjectKey();

    }





}
