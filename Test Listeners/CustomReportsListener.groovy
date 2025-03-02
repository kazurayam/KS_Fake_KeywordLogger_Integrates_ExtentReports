import com.kazurayam.ks.reporting.ReportBuilder
import com.kazurayam.ks.reporting.ReportBuilderExtentImpl
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.configuration.RunConfiguration

class CustomReportsListener {

	boolean runAsTestSuite = false
    
	@BeforeTestSuite
	def beforeTestSuite(TestSuiteContext testSuiteContext) {
    	ReportBuilderExtentImpl.getInstance().createExtentReports(testSuiteContext,
			"document title", "report name")
        runAsTestSuite = true
	}

	@BeforeTestCase
	def beforeTestCase(TestCaseContext testCaseContext) {
		if (!runAsTestSuite) {
    		ReportBuilderExtentImpl.getInstance().createExtentReports(null,
				"document title", "report name")
		}
		ReportBuilderExtentImpl.getInstance().createExtentTest(testCaseContext)
	}

	@AfterTestCase
	def afterTestCase(TestCaseContext testCaseContext) throws IOException {
		ReportBuilderExtentImpl.getInstance().flushReport()
	}

	@AfterTestSuite
	def afterTestSuite() {
		ReportBuilderExtentImpl.getInstance().flushReport()
	}

}
