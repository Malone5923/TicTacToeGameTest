package Tic.Tac.Toe.Game;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class TestCases extends Base {

	@Test
	public void verifyValidUserCanSignIn() {

		driver.findElement(By.cssSelector("#email")).sendKeys(prop.getProperty("loginEmail"));
		driver.findElement(By.cssSelector("#password")).sendKeys(prop.getProperty("loginPass"));
		driver.findElement(By.cssSelector("#submit")).click();
		WebDriverWait wait = new WebDriverWait(driver, 40, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#userEmail")));
		String username = driver.findElement(By.cssSelector("#userEmail")).getText();
		Assert.assertEquals(prop.getProperty("loginEmail"), username);
		driver.findElement(By.cssSelector("#sign-out")).click();		
	}
	
	@Test
	public void verifyUserCanSignUp() {
		
		driver.findElement(By.xpath("//a[contains(text(),'Register')]")).click();
		driver.findElement(By.cssSelector("#fullName")).sendKeys(prop.getProperty("SignUpName"));
		driver.findElement(By.cssSelector("#email")).sendKeys(prop.getProperty("SignUpEmail"));
		driver.findElement(By.cssSelector("#birthday")).click();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
