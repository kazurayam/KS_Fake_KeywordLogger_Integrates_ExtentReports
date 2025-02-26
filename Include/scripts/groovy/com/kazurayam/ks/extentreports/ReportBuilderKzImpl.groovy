package com.kazurayam.ks.extentreports

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports
import com.aventstack.extentreports.ExtentTest
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import com.aventstack.extentreports.reporter.ExtentSparkReporter
import com.aventstack.extentreports.reporter.configuration.Theme
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.webui.driver.DriverFactory

/**
 *
 * @author kazurayam
 */
public class ReportBuilderKzImpl extends ReportBuilder {

	// The "Bill Pugh Singleton" pattern is applied
	// See https://www.baeldung.com/java-bill-pugh-singleton-implementation
	private class SingletonHolder {
		private static final ReportBuilderKzImpl INSTANCE = new ReportBuilderKzImpl()
	}

	public static ReportBuilderKzImpl getInstance() {
		return SingletonHolder.INSTANCE
	}

	private ReportBuilderKzImpl() {}

	private ExtentSparkReporter sparkReporter
	private ExtentReports extentReports
	private ExtentTest extentTest
	private String executionSourceName = "TestCase"
	private String reportName
	private Path projectPath
	private Path reportPath

	/**
	 * @param testSuiteContext not used
	 */
	@Override
	void createExtentReports(TestSuiteContext testSuiteContext,
			String documentTitle, String reportTitle,
			String projectDir = System.getProperty("user.dir")) {
		reportName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
		projectPath = Paths.get(projectDir)
		Path folderPath = projectPath.resolve("ExtentKz").resolve(reportName)
		if (!Files.exists(folderPath)) {
			try {
				Files.createDirectories(folderPath);
			} catch (IOException e) {
				System.err.println("Failed to create the folder: " + e.getMessage());
			}
		}
		reportPath = folderPath.resolve(reportTitle.replaceAll(" ", "_") + ".html")
		sparkReporter = new ExtentSparkReporter(reportPath.toString())
		sparkReporter.config().setDocumentTitle(documentTitle)
		sparkReporter.config().setReportName(reportTitle)
		sparkReporter.config().setTheme(Theme.STANDARD)
		sparkReporter.config().setTimelineEnabled(true)
		sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'")

		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("Operating System: ", System.getProperty("os.name"))
		extentReports.setSystemInfo("Java Version: ", System.getProperty("java.version"))
		extentReports.setSystemInfo("Host Address: ", RunConfiguration.getHostAddress())
		extentReports.setSystemInfo("Host Name: ", RunConfiguration.getHostName())
	}

	@Override
	void createExtentTest(TestCaseContext testCaseContext) {
		String browser = DriverFactory.getExecutedBrowser().getName()
		String execID = RunConfiguration.getExecutionSourceName()
		String testCaseName = testCaseContext.getTestCaseId()
				.substring(testCaseContext.getTestCaseId().lastIndexOf('/') + 1)
		extentTest = extentReports.createTest(execID + " : " + testCaseName, "Test Execution: " + testCaseContext.getTestCaseId())
		extentTest.assignAuthor("HOST: " + RunConfiguration.getHostName())
		extentTest.assignCategory("Browser: " + browser)
		extentTest.assignCategory("PROFILE: " + RunConfiguration.getExecutionProfile().toUpperCase())
		extentTest.assignCategory("GROUP: " + TestCaseFactory.findTestCase(testCaseContext.getTestCaseId()).getTag().toUpperCase())
	}

	@Override
	void flushExtentReports() {
		extentReports.flush()
	}

	@Override
	void logInfo(String message) {
		extentTest.log(Status.PASS, message)
	}

	@Override
	Path getReportPath() {
		return reportPath
	}

	@Override
	String getReportContent() {
	}
}