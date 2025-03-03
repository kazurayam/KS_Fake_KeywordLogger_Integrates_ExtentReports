package com.kazurayam.ks.reporting
import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4.class)
public class ReportBuildersLoaderTest {
	
	@Test
	void testSmoke() {
		int expected = 21
		int actual = 21
		assertThat(actual, is(expected))
	}

}
