package com.kazurayam.ks.reporting

import java.nio.file.Path

public class ReportAdapterConsoleImpl implements ReportAdapter {

	// The "Bill Pugh Singleton" pattern is applied
	// See https://www.baeldung.com/java-bill-pugh-singleton-implementation
	private class SingletonHolder {
		private static final ReportAdapterConsoleImpl INSTANCE = new ReportAdapterConsoleImpl()
	}

	public static ReportAdapterConsoleImpl getInstance() {
		return SingletonHolder.INSTANCE
	}

	private ReportAdapterConsoleImpl() {}

	@Override
	void flushReport() {}

	@Override
	void logDebug(String message) {
		//println "[DEBUG] " + message
	}

	@Override
	void logInfo(String message) {
		println "[INFO] " + message
	}

	@Override
	void logPassed(String message) {
		println "[PASS] " + message
	}

	@Override
	void logWarning(String message) {
		println "[WARN] " + message
	}

	@Override
	void logFailed(String message) {
		println "[FAIL] " + message
	}

	@Override
	void logFailed(String message, Throwable t) {
		println "[FAIL] " + message
	}

	@Override
	void addImageFromPath(String path) {
		// does nothing
	}

	Path getReportPath() {
		return null
	}
}
