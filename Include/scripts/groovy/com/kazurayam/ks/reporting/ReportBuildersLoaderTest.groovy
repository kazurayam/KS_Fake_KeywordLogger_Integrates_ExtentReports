package com.kazurayam.ks.reporting

import com.kazurayam.ks.reporting.ReportBuilder

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4.class)
public class ReportBuildersLoaderTest {

	@Test
	void test_loadReportBuilders() {
		Map<String, ReportBuilder> cache = ReportBuildersLoader.load();
		assertNotNull(cache);
		assertTrue("the cache has no content", cache.size() > 0)
		assertEquals("expected the cache to contain 2 entities but not", 2, cache.size())
	}
}
