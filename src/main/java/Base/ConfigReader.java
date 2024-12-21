package Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader(String configFilePath) {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(configFilePath);
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProjectKey() {
        return properties.getProperty("jira.project.key");
    }

    public String getEmail() {
        return properties.getProperty("jira.email");
    }

    public String getApiToken() {
        return properties.getProperty("jira.api.token");
    }

    public String getBaseUrl() {
        return properties.getProperty("jira.base.url");
    }
}
