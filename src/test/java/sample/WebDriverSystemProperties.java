package sample;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverSystemProperties {

  protected static WebDriver webDriver;

  @BeforeClass
  public static void tearUp() {
    WebDriverSystemProperties.setWebDriverProperties(System.getProperty("os.name"));
    webDriver = new ChromeDriver();
    webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    webDriver.get("http://automationpractice.com/index.php");
    webDriver.manage().window().maximize();
  }

  @AfterClass
  public static void tearDown() {
    webDriver.quit();
  }

  public static void setWebDriverProperties(String osName) {
    if (osName.contains("Windows")) {
      setWindowsWebDriver();
    } else if (osName.contains("Linux")) {
      setLinuxWebDriver();
    } else {
      System.out.println("Don't have support for this SO: " + osName);
      System.exit(1);
    }
  }

  private static void setWindowsWebDriver() {
    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
  }

  private static void setLinuxWebDriver() {
    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
  }
}
