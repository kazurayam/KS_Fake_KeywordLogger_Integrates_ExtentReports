package com.kazurayam.ks.reporting

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import com.aventstack.extentreports.ExtentReports
import com.aventstack.extentreports.ExtentTest
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
public class ReportBuilderExtentImpl implements ReportBuilder {

	// The "Bill Pugh Singleton" pattern is applied
	// See https://www.baeldung.com/java-bill-pugh-singleton-implementation
	private class SingletonHolder {
		private static final ReportBuilderExtentImpl INSTANCE = new ReportBuilderExtentImpl()
	}
	public static ReportBuilderExtentImpl getInstance() {
		return SingletonHolder.INSTANCE
	}
	private ReportBuilderExtentImpl() {}

	//
	private static final String OUTPUT_DIR_NAME = "Extent"
	private Path projectPath
	private Path reportPath

	private ExtentReports extent
	private ExtentTest logger

	/**
	 * @param testSuiteContext not really used
	 */
	void createExtentReports(TestSuiteContext testSuiteContext,
			String documentTitle,
			String reportTitle,
			Path projectPath = Paths.get(RunConfiguration.getProjectDir())) {
		//assert testSuiteContext != null
		assert documentTitle != null
		assert reportTitle != null
		assert projectPath != null
		assert Files.exists(projectPath)
		//
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
		Path folderPath = projectPath.resolve(OUTPUT_DIR_NAME).resolve(timestamp)
		ensureDirectory(folderPath)
		reportPath = folderPath.resolve(reportTitle.replaceAll(" ", "_") + ".html")
		//
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath.toString())
		sparkReporter.config().setDocumentTitle(documentTitle)
		sparkReporter.config().setReportName(reportTitle)
		sparkReporter.config().setTheme(Theme.STANDARD)
		sparkReporter.config().setTimelineEnabled(true)
		sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'")
		//
		extent = new ExtentReports()
		extent.attachReporter(sparkReporter)
		extent.setSystemInfo("Operating System: ", System.getProperty("os.name"))
		extent.setSystemInfo("Java Version: ", System.getProperty("java.version"))
		extent.setSystemInfo("Host Address: ", RunConfiguration.getHostAddress())
		extent.setSystemInfo("Host Name: ", RunConfiguration.getHostName())
	}

	private void ensureDirectory(Path directory) throws IOException {
		if (!Files.exists(directory)) {
			Files.createDirectories(directory);
		}
	}

	void createExtentTest(TestCaseContext tcContext) {
		assert tcContext != null
		//
		String browser = DriverFactory.getExecutedBrowser().getName()
		String execID = RunConfiguration.getExecutionSourceName()
		String testCaseName = tcContext.getTestCaseId()
				.substring(tcContext.getTestCaseId().lastIndexOf('/') + 1)
		logger = extent.createTest(execID + " : " + testCaseName, "Test Execution: " + tcContext.getTestCaseId())
		logger.assignAuthor("Host: " + RunConfiguration.getHostName())
		logger.assignCategory("Browser: " + browser)
		logger.assignCategory("Profile: " + RunConfiguration.getExecutionProfile().toUpperCase())
		//extentTest.assignCategory("GROUP: " + TestCaseFactory.findTestCase(testCaseContext.getTestCaseId()).getTag().toUpperCase())
	}

	@Override
	void flushReport() {
		if (extent != null) {
			extent.flush()
		}
	}

	@Override
	void logDebug(String message) {
		if (logger != null) {
			// we ignore messages of Debug level
			//logger.log(Status.INFO, message)
		}
	}

	@Override
	void logFailed(String message) {
		if (logger != null) {
			logger.fail(message)
		}
	}

	@Override
	void logFailed(String message, Throwable t) {
		if (logger != null) {
			logger.fail(t)
		}
	}

	@Override
	void addImageFromPath(String path) {
		//println "[ReportBuilderExtentImpl#addImageFromPath] path=${path}"
		if (logger != null) {
			logger.addScreenCaptureFromPath(path)
		}
	}

	@Override
	void logInfo(String message) {
		if (logger != null) {
			logger.log(Status.INFO, message)
		}
	}

	@Override
	void logPassed(String message) {
		if (logger != null) {
			logger.log(Status.PASS, message)
		}
	}

	@Override
	void logWarning(String message) {
		if (logger != null) {
			logger.log(Status.WARNING, message)  // I can make it better
		}
	}

	Path getReportPath() {
		return reportPath
	}
}
