package com.kazurayam.ks.reporting

import java.nio.file.Path

interface ReportBuilder {

	abstract void flushReport()

	abstract void logInfo(String details)

	abstract Path getReportPath()

	abstract String getReportContent()
}