import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

TestObject makeTestObject(String id, String xpath) {
	TestObject tObj = new TestObject(id)
	tObj.addProperty("xpath", ConditionType.EQUALS, xpath)
	return tObj
}
TestObject validBtn = makeTestObject("validBtn", "//a[@id='btn-make-appointment']")
TestObject invalidBtn = makeTestObject("invalidBtn", "//a[@id='btn-make-disappointment']")

WebUI.openBrowser("http://demoaut.katalon.com/")
//WebUI.verifyElementPresent(validBtn, 10, FailureHandling.OPTIONAL)
WebUI.verifyElementPresent(invalidBtn, 10, FailureHandling.CONTINUE_ON_FAILURE)
//WebUI.verifyElementPresent(invalidBtn, 10, FailureHandling.STOP_ON_FAILURE)
WebUI.closeBrowser()

