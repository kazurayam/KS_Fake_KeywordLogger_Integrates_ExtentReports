package com.kazurayam.ks.reporting

import java.nio.file.Path

interface ReportBuilder {

	abstract void flushReport()

	abstract void logDebug(String message)
	
	abstract void logInfo(String message)
	
	abstract void logPassed(String message)

	abstract Path getReportPath()

	abstract String getReportContent()
}