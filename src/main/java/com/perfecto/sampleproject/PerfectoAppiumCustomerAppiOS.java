package com.perfecto.sampleproject;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResult;
import com.perfecto.reportium.test.result.TestResultFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class PerfectoAppiumCustomerAppiOS {
	IOSDriver<IOSElement> driver;
	ReportiumClient reportiumClient;

	@Test
	public void appiumTest() throws Exception {
		// Replace <<cloud name>> with your perfecto cloud name (e.g. demo) or pass it as maven properties: -DcloudName=<<cloud name>>
		String cloudName = "trial";
		
		// Replace <<security token>> with your perfecto security token or pass it as maven properties: -DsecurityToken=<<SECURITY TOKEN>>  More info: https://developers.perfectomobile.com/display/PD/Generate+security+tokens
		String securityToken = "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI2ZDM2NmJiNS01NDAyLTQ4MmMtYTVhOC1kODZhODk4MDYyZjIifQ.eyJpYXQiOjE2ODA1MzMzMTYsImp0aSI6IjM3NjQ4NmU5LTc0ZDEtNDFkMi1iM2M3LWNlOTFlM2NlNDM3ZiIsImlzcyI6Imh0dHBzOi8vYXV0aDMucGVyZmVjdG9tb2JpbGUuY29tL2F1dGgvcmVhbG1zL3RyaWFsLXBlcmZlY3RvbW9iaWxlLWNvbSIsImF1ZCI6Imh0dHBzOi8vYXV0aDMucGVyZmVjdG9tb2JpbGUuY29tL2F1dGgvcmVhbG1zL3RyaWFsLXBlcmZlY3RvbW9iaWxlLWNvbSIsInN1YiI6IjMzMjI0OWYwLWI4ZjYtNDNiYS05ZmJmLWZhYTcxMmNjZGNhYiIsInR5cCI6Ik9mZmxpbmUiLCJhenAiOiJvZmZsaW5lLXRva2VuLWdlbmVyYXRvciIsIm5vbmNlIjoiZGUwYWE3Y2YtNTc4Zi00YjdiLThhMDgtODIzN2M2NThiNjIxIiwic2Vzc2lvbl9zdGF0ZSI6ImU1MmQwNmJkLTA2MWYtNGE4NC04ZTVlLWVlYzZiYjY2Y2JjYyIsInNjb3BlIjoib3BlbmlkIG9mZmxpbmVfYWNjZXNzIHByb2ZpbGUgZW1haWwifQ.MMxmhlvSxQ1WJ-S1m1_ggoueIwLT5NQ3z2qtO7IBs6A";
		
		cloudName = PerfectoLabUtils.fetchCloudName(cloudName);
		securityToken = PerfectoLabUtils.fetchSecurityToken(securityToken);


		//Mobile: Auto generate capabilities for device selection: https://developers.perfectomobile.com/display/PD/Select+a+device+for+manual+testing#Selectadeviceformanualtesting-genCapGeneratecapabilities
		String browserName = "mobileOS";
		DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
		capabilities.setCapability("model", "Galaxy S.*|LG.*");
		capabilities.setCapability("enableAppiumBehavior", true);
		capabilities.setCapability("openDeviceTimeout", 2);
		capabilities.setCapability("appPackage", "<<MY App package>>"); // Set the unique identifier of your app
		capabilities.setCapability("autoLaunch", true); // Whether to install and launch the app automatically.
		capabilities.setCapability("takesScreenshot", false);
		capabilities.setCapability("screenshotOnError", true); // Take screenshot only on errors

		// The below capability is mandatory. Please do not replace it.
		capabilities.setCapability("securityToken", securityToken);

		driver = new IOSDriver<IOSElement>(new URL("https://" + cloudName  + ".perfectomobile.com/nexperience/perfectomobile/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		reportiumClient = PerfectoLabUtils.setReportiumClient(driver, reportiumClient); //Creates reportiumClient
		reportiumClient.testStart("Android Java Native Sample", new TestContext("tag2", "tag3")); //Starts the reportium test

		reportiumClient.stepStart("first step");
		WebDriverWait wait = new WebDriverWait(driver, 30);

		// Enter your code here
		reportiumClient.stepEnd();
		// Add as many test steps as needed


	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		TestResult testResult = null;
		if(result.getStatus() == result.SUCCESS) {
			testResult = TestResultFactory.createSuccess();
		}
		else if (result.getStatus() == result.FAILURE) {
			testResult = TestResultFactory.createFailure(result.getThrowable());
		}
		reportiumClient.testStop(testResult);

		driver.close();
		driver.quit();
		// Retrieve the URL to the DigitalZoom Report
		String reportURL = reportiumClient.getReportUrl();
		System.out.println(reportURL);
	}



}

