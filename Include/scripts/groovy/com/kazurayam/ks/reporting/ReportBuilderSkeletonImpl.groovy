package com.kazurayam.ks.reporting

import java.nio.file.Path

public class ReportBuilderSkeletonImpl implements ReportBuilder {

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
	void logInfo(String message) {
		println message
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
