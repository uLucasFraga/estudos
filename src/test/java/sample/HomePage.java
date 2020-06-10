package sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class HomePage extends PageObject {

  private static final long DEFAULT_TIMEOUT_WAIT = 10;

  @FindBy(id = "search_query_top")
  private WebElement searchQuery;

  @FindBy(name = "submit_search")
  private WebElement submitSearch;

  @FindBy(css = "p[class='alert alert-warning']")
  private WebElement alertWarning;

  @FindBy(css = ".right-block h5 a")
  private WebElement messageCorrect;

  public HomePage(WebDriver webDriver) {
    super(webDriver);
  }

  public void setSearchQuery(String search) {
    WebDriverWait wait = new WebDriverWait(webDriver, DEFAULT_TIMEOUT_WAIT);
    wait.until(ExpectedConditions.elementToBeClickable(searchQuery));
    this.searchQuery.clear();
    this.searchQuery.sendKeys(search);
  }

  public HomePage submit() {
    submitSearch.click();
    return new HomePage(webDriver);
  }

  public void searchAndClick(String search) {
    setSearchQuery(search);
    submit();
  }

  public void getMessageError(String text) {
    String alert = this.alertWarning.getText().trim();
    assertEquals("Error trying to do a search!", text, alert);
  }

  public void getMessageCorrect(String text) {
    String alert = this.messageCorrect.getText().trim();
    assertEquals("Error trying to do a search!", text, alert);
  }
}
