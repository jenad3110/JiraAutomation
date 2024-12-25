package testCaseCreatorUnderTest;

public class TestCase {
    private String summary;
    private String testStatus;
    private String actualResults;
    private String expectedResults;
    private String testData;
    private String testSteps;

    public TestCase(String summary, String testStatus, String actualResults, String expectedResults, String testData, String testSteps) {
        this.summary = summary;
        this.testStatus = testStatus;
        this.actualResults = actualResults;
        this.expectedResults = expectedResults;
        this.testData = testData;
        this.testSteps = testSteps;
    }

    public String getSummary() {
        return summary;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public String getActualResults() {
        return actualResults;
    }

    public String getExpectedResults() {
        return expectedResults;
    }

    public String getTestData() {
        return testData;
    }

    public String getTestSteps() {
        return testSteps;
    }
}
