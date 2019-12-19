package com.qa.DemoSiteAuto.tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.DemoSiteAuto.consts.Constants;
import com.qa.DemoSiteAuto.pages.Login;
import com.qa.DemoSiteAuto.pages.Register;

public class LogInTestPOM {

	private WebDriver driver;

	private final String USER = "jboss";
	private final String PASS = "pass";

	@Before
	public void setup() {
		System.setProperty(Constants.PROPERTY, Constants.PATH);
		driver = new ChromeDriver();
	}

	@Test
	public void test() {
		this.driver.get("http://thedemosite.co.uk/addauser.php");
		Register registerPage = PageFactory.initElements(driver, Register.class);
		registerPage.enterUsername(USER);
		registerPage.enterPassword(PASS);
		registerPage.submit();
		registerPage.navigateToLogin();
		Login loginPage = PageFactory.initElements(driver, Login.class);
		loginPage.enterUsername(USER);
		loginPage.enterPassword(PASS);
		loginPage.login();
		assertTrue("Login failed", loginPage.getLoginMessage().contains("Successful Login"));
	}

	@After
	public void tearDown() {
		this.driver.close();
	}

}
