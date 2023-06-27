package com.perfecto.sampleproject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.perfecto.sampleproject.PerfectoLabUtils;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class perfecto {
	
	public static void main(String[] args) throws Exception {
		
		String securityToken = "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI2ZDM2NmJiNS01NDAyLTQ4MmMtYTVhOC1kODZhODk4MDYyZjIifQ.eyJpYXQiOjE2Nzk0NTY5NjksImp0aSI6IjhmNzc1YzZmLTRiZmQtNDdlYS04ODZiLTkwMzhhMjdmNjc5NSIsImlzcyI6Imh0dHBzOi8vYXV0aDMucGVyZmVjdG9tb2JpbGUuY29tL2F1dGgvcmVhbG1zL3RyaWFsLXBlcmZlY3RvbW9iaWxlLWNvbSIsImF1ZCI6Imh0dHBzOi8vYXV0aDMucGVyZmVjdG9tb2JpbGUuY29tL2F1dGgvcmVhbG1zL3RyaWFsLXBlcmZlY3RvbW9iaWxlLWNvbSIsInN1YiI6IjI2MTAzM2QyLWM5NTUtNGMzZS04NjNiLTYwODgzN2E4ZmQwNiIsInR5cCI6Ik9mZmxpbmUiLCJhenAiOiJvZmZsaW5lLXRva2VuLWdlbmVyYXRvciIsIm5vbmNlIjoiOTdjZDdmZDMtMWFlNy00NGI0LThjZjEtNTNiM2YwNGUyM2E0Iiwic2Vzc2lvbl9zdGF0ZSI6IjQzZjlhZTdlLTNmODgtNGQ4Yy04Y2VhLWRjOWIwOTJjYzFiZSIsInNjb3BlIjoib3BlbmlkIG9mZmxpbmVfYWNjZXNzIHByb2ZpbGUgZW1haWwifQ.KWbOPED2BfT6bDB8X8gOuVpA-IQ4fquxHT4FWee8PlU";
		
		DesiredCapabilities capabilities = new DesiredCapabilities("", "", Platform.ANY);
		capabilities.setCapability("securityToken", PerfectoLabUtils.fetchSecurityToken(securityToken));
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("platformVersion", "14.2");
		capabilities.setCapability("platformBuild", "18B92");
		capabilities.setCapability("location", "NA-US-BOS");
		capabilities.setCapability("resolution", "1125x2436");
		capabilities.setCapability("accountName", "dev1");
		capabilities.setCapability("deviceStatus", "CONNECTED");
		capabilities.setCapability("manufacturer", "Apple");
		capabilities.setCapability("model", "iPhone-11 Pro");
		
		URL url = new URL("https://trial.app.perfectomobile.com/nexperience/perfectomobile/wd/hub");
		AndroidDriver<MobileElement> driver = new AndroidDriver<MobileElement>(url, capabilities);
		
		// Navigate to the Google homepage
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.google.com");

		// Search for "Perfecto Mobile"
		MobileElement searchBox = driver.findElementByName("q");
		searchBox.sendKeys("Perfecto Mobile");
		searchBox.submit();

		// Verify that the search results are displayed
		MobileElement results = driver.findElementByXPath("//div[@id='search']");
		assert results.isDisplayed();

		// Close the browser and quit the driver
		driver.closeApp();
		driver.quit();
	}
}
