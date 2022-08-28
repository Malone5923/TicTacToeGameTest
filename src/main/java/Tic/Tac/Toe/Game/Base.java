package Tic.Tac.Toe.Game;

//import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;

public class Base {

	protected SoftAssert softassert = new SoftAssert();
	public WebDriver driver;
	protected Properties prop = new Properties();
	private String USER_DIR = System.getProperty("user.dir");

	public WebDriver initializeDriver() throws IOException {

		FileInputStream fis = new FileInputStream(USER_DIR + "\\src\\main\\java\\Tic\\Tac\\Toe\\Game\\data.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.contains("headless")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--disable-gpu");
			driver = new ChromeDriver(options);
		} else if (browserName.contains("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.contains("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

//	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
//		TakesScreenshot ts = (TakesScreenshot) driver;
//		File source = ts.getScreenshotAs(OutputType.FILE);
//		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
//		FileUtils.copyFile(source, new File(destinationFile));
//		return destinationFile;
//
//	}

	public WebDriver getDriver() {
		return driver;
	}

	@BeforeClass
	public void SetUp() throws IOException {

		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

//	public int checkConnection(String url) throws MalformedURLException, IOException {
//		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
//		conn.setRequestMethod("HEAD");
//		conn.connect();
//		int respCode = conn.getResponseCode();
//		log.info("Response Code received: " + respCode);
//		return respCode;
//	}

}