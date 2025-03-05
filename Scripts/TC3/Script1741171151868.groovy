import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

TestObject makeTestObject(String id, String xpath) {
	TestObject tObj = new TestObject(id)
	tObj.addProperty("xpath", ConditionType.EQUALS, xpath)
	return tObj
}
TestObject validButton = makeTestObject("validButton", "//a[@id='btn-make-appointment']")
TestObject invalidButton = makeTestObject("validButton", "//a[@id='btn-make-disappointment']")

WebUI.openBrowser("http://demoaut.katalon.com/")
WebUI.verifyElementPresent(validButton, 10, FailureHandling.OPTIONAL)
WebUI.verifyElementPresent(invalidButton, 10, FailureHandling.OPTIONAL)
WebUI.verifyElementPresent(invalidButton, 10, FailureHandling.OPTIONAL)
WebUI.closeBrowser()

