package com.kazurayam.ks.reporting

import java.nio.file.Path

public class ReportBuilderSkeletonImpl implements ReportBuilder {

	// The "Bill Pugh Singleton" pattern is applied
	// See https://www.baeldung.com/java-bill-pugh-singleton-implementation
	private class SingletonHolder {
		private static final ReportBuilderSkeletonImpl INSTANCE = new ReportBuilderSkeletonImpl()
	}

	public static ReportBuilderSkeletonImpl getInstance() {
		return SingletonHolder.INSTANCE
	}

	private ReportBuilderSkeletonImpl() {}

	@Override
	void flushReport() {}

	@Override
	void logDebug(String message) {
		println "DEBUG " + message
	}
	
	@Override
	void logInfo(String message) {
		println "INFO " + message
	}

	@Override
	void logPassed(String message) {
		println "PASS " + message
	}
	
	@Override
	Path getReportPath() {
		return null
	}

	@Override
	String getReportContent() {
		return null
	}
}
