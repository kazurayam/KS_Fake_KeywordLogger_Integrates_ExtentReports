package com.kazurayam.ks.extentreports

import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.configuration.RunConfiguration
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.FileVisitResult
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes;

abstract class ReportBuilder {

	abstract void createExtentReports(TestSuiteContext testSuiteContext, String documentTitle, String reportName, String projectDir)

	abstract void createExtentTest(TestCaseContext testCaseContext)

	abstract void flushExtentReports()

	abstract void logInfo(String details)

	abstract Path getReportPath()

	abstract String getReportContent()
}