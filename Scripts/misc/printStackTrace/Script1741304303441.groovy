import com.kazurayam.ks.reporting.ReportBuilderExtentImpl
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

try {
	def expected = 2
	def actual = 3
	WebUI.verifyEqual(expected, actual)
} catch (Exception e) {
	ReportBuilderExtentImpl.getInstance().logFailed(e.getMessage())
}
