package com.kms.katalon.core.logging

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4.class)

public class XmlKeywordLoggerTest {

	@Test
	public void test_getAttributesFrom() {
		try {
			throw new IllegalStateException("This is a fake exception")
		} catch (Exception e) {
			Map<String,String> attributes = XmlKeywordLogger.getInstance().getAttributesFrom(e)
			println attributes
		}
	}
}
