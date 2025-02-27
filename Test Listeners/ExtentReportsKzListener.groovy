import com.kazurayam.ks.extentreports.ReportBuilder
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext

class ExtentReportsKzListener {
	
	boolean runAsTestSuite = false

	@BeforeTestSuite
	def beforeTestSuite(TestSuiteContext testSuiteContext) {
		ReportBuilder.getDefaultInstance().createExtentReports(testSuiteContext, 
			"document title", "report name")
		runAsTestSuite = true
	}

	@BeforeTestCase
	def beforeTestCase(TestCaseContext testCaseContext) {
		if (!runAsTestSuite) {
			ReportBuilder.getDefaultInstance().createExtentReports(null,
				"document title", "report name")
		}
		ReportBuilder.getDefaultInstance().createExtentTest(testCaseContext)
	}

	@AfterTestCase
	def afterTestCase(TestCaseContext testCaseContext) throws IOException {
		//ReportBuilderKzImpl.getInstance().takeScreenshotFailure(testCaseContext)   // may throw com.kms.katalon.core.webui.exception.BrowserNotOpenedException
		ReportBuilder.getDefaultInstance().flushExtentReports()
	}

	@AfterTestSuite
	def afterTestSuite() {
		ReportBuilder.getDefaultInstance().flushExtentReports()
	}

}