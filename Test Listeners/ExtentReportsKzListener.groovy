import com.kazurayam.ks.reporting.ReportBuilder
import com.kazurayam.ks.reporting.ReportBuilderKzImpl
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
		ReportBuilderKzImpl.getInstance().createExtentReports(testSuiteContext,
			"document title", "report name")
		runAsTestSuite = true
	}

	@BeforeTestCase
	def beforeTestCase(TestCaseContext testCaseContext) {
		if (!runAsTestSuite) {
			ReportBuilderKzImpl.getInstance().createExtentReports(null,
				"document title", "report name")
		}
		ReportBuilderKzImpl.getInstance().createExtentTest(testCaseContext)
	}

	@AfterTestCase
	def afterTestCase(TestCaseContext testCaseContext) throws IOException {
		ReportBuilderKzImpl.getInstance().flushReport()
	}

	@AfterTestSuite
	def afterTestSuite() {
		ReportBuilderKzImpl.getInstance().flushReport()
	}

}
