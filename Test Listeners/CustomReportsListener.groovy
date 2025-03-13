import com.kazurayam.ks.reporting.ReportAdapter
import com.kazurayam.ks.reporting.ReportAdapterExtentImpl
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
    	ReportAdapterExtentImpl.getInstance().createExtentReports(testSuiteContext,
			"document title", "report name")
        runAsTestSuite = true
	}

	@BeforeTestCase
	def beforeTestCase(TestCaseContext testCaseContext) {
		if (!runAsTestSuite) {
    		ReportAdapterExtentImpl.getInstance().createExtentReports(null,
				"document title", "report name")
		}
		ReportAdapterExtentImpl.getInstance().createExtentTest(testCaseContext)
	}

	@AfterTestCase
	def afterTestCase(TestCaseContext testCaseContext) throws IOException {
		ReportAdapterExtentImpl.getInstance().flushReport()
	}

	@AfterTestSuite
	def afterTestSuite() {
		ReportAdapterExtentImpl.getInstance().flushReport()
	}

}
