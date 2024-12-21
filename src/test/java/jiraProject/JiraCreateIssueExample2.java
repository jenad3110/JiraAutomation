package jiraProject;

import Base.CommonAPI;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.ArrayList;
import java.util.List;

public class JiraCreateIssueExample2 extends CommonAPI{

    public static void main(String[] args) {


        retrieveCredentials();
        List<Issue> issues = new ArrayList<>();

        /*
        issues.add(new Issue("Create Maven Project", "Create a Maven Quick-start Project and add dependencies for WebDriverManager, Selenium Java, TestNG, Log4j (log4j-api and log4j-core), Apache Commons IO, and ExtentReports."));
        issues.add(new Issue("Set Up Base Class", "Create a Base class under a new package 'resources' in 'src/main/java'. Implement a method 'initializeBrowser' that reads the browser name from a properties file (data.properties) located in the resources package."));
        issues.add(new Issue("Implement Browser Initialization", "Add 'browser=chrome' to data.properties file and modify the 'initializeBrowser' method to read the browser property."));
        issues.add(new Issue("Create LoginTest Class", "Create 'LoginTest' class under 'src/test/java' in 'tests' package. Implement a 'login' method annotated with @Test. Extend the LoginTest class with Base class and initialize the browser to open the application URL."));
        issues.add(new Issue("Externalize URL", "Add 'url=http://tutorialsninja.com/demo/' to data.properties file and modify LoginTest to read the URL from the properties file. Verify execution."));
        issues.add(new Issue("Create Page Objects", "Create 'pageobjects' package under 'src/main/java'. Create classes 'LandingPage', 'LoginPage', and 'AccountPage' with respective page objects. Instantiate these classes in LoginTest and perform required operations."));
        issues.add(new Issue("Parameterize LoginTest", "Remove hardcoded email and password from LoginTest, read them from properties file, and implement @AfterMethod to close the browser. Parameterize the test using DataProvider annotated method."));
        issues.add(new Issue("Organize Test Initialization", "Create @BeforeMethod for opening the application and create dummy test classes (TestTwo, TestThree, TestFour). Convert the project to TestNG and execute tests as a group using testng.xml file."));
        issues.add(new Issue("Integrate with Maven", "Search for Maven TestNG configuration and add Maven Surefire plugin. Run the tests from command line using 'mvn test'."));
        issues.add(new Issue("Configure Log4j", "Copy log4j2.xml file to the resources package. Write logs in LoginTest, add log4j configuration tags to pom.xml, and add Maven filtering tags under <build>. Run the code to see logs in log files."));
        issues.add(new Issue("Enable Parallel Execution", "Modify testng.xml file to enable parallel execution by separating individual classes to separate tags and adding 'parallel=tests'. Verify parallel execution."));
        issues.add(new Issue("Capture Screenshots for Failures", "Create 'listeners' package under 'src/main/java' and implement ITestListener in Listeners class. Override required methods and implement screenshot functionality in Base class. Update onTestFailure method and configure testng.xml file to include listeners."));
        issues.add(new Issue("Integrate ExtentReports", "Create 'utilities' package under 'src/main/java' and implement 'ExtentReporter' class with 'getExtentReport' method. Write extent report code in Listeners methods and make ExtentReports thread-safe. Enable parallel execution in testng.xml file."));
        issues.add(new Issue("Add Screenshots to ExtentReports", "Modify takeScreenshot() method to return the file path. Update onTestFailure method to add screenshot to ExtentReports."));
        issues.add(new Issue("Apply Encapsulation", "Make all Page Objects private. Integrate Cucumber BDD into the framework by adding dependencies for Cucumber, creating feature files, step definitions, and a runner class. Run tests using testng2.xml file and configure Maven Surefire plugin to use this file."));
        issues.add(new Issue("Git and GitHub Setup", "Create a GitHub account and repository. Install git, initialize a local repository, configure user details, clone the GitHub repo, and commit/push code to GitHub. Import the code into Eclipse IDE and perform basic git operations like adding, committing, pushing, pulling, branching, and merging."));
        issues.add(new Issue("Jenkins Integration", "Launch Jenkins, install Maven Integration Plugin, create a new Maven job, configure Git as source code management, and set up build steps to invoke Maven targets. Configure build triggers and post-build actions."));
        issues.add(new Issue("SauceLabs Integration", "Create a SauceLabs account, configure Selenium for remote execution, and set up SauceLabs capabilities. Write sample Selenium code and execute tests on SauceLabs."));
*/
        issues.add(new Issue("Create an issue using Java and API ", "this is a test "));
        for (Issue issue : issues) {
            createIssue(issue);
        }
    }

    public static void createIssue(Issue issue) {
        try {
            URL url = new URL(JIRA_BASE_URL + "/rest/api/2/issue");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            String auth = USER_EMAIL + ":" + API_TOKEN;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
            connection.setRequestProperty("Authorization", "Basic " + encodedAuth);

            connection.setDoOutput(true);

            String jsonInputString = "{"
                    + "\"fields\": {"
                    + "\"project\": {"
                    + "\"key\": \"" + PROJECT_KEY + "\""
                    + "},"
                    + "\"summary\": \"" + issue.getTitle() + "\","
                    + "\"description\": \"" + issue.getDescription() + "\","
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
                System.out.println("Issue created successfully: " + issue.getTitle());
            } else {
                System.out.println("Failed to create issue: " + issue.getTitle() + " - Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Issue {
        private String title;
        private String description;

        public Issue(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }


}

