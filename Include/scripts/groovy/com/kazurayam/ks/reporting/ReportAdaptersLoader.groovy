package com.kazurayam.ks.reporting

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.concurrent.ConcurrentHashMap
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.configuration.RunConfiguration

import groovy.json.JsonSlurper

public class ReportAdaptersLoader {

	public static final String CONFIG_FILE_NAME = "reporting.config.json"

	private ReportAdaptersLoader() {}

	public static final Map<String, ReportAdapter> load() {
		Map<String, ReportAdapter> reportBuilders = new ConcurrentHashMap<>()
		Path configPath = Paths.get(RunConfiguration.getProjectDir()).resolve(CONFIG_FILE_NAME)
		if (Files.exists(configPath)) {
			def json = new JsonSlurper().parse(configPath)
			json["ReportAdapter_classes"].each { className ->
				try {
					Class clazz = Class.forName(className)
					Object singleton = clazz.getInstance()
					reportBuilders.put(className, singleton)
				} catch (Exception e) {
					e.printStackTrace()
				}
			}
		}
		return reportBuilders
	}
}
