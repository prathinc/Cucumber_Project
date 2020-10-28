package stepDefinitions;

import java.io.File;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AlchemyJobs {
	private WebDriver driver = null;
    private WebDriverWait wait = null;
    
    @Given("^User opens the Bowser$")
    public void OpenBrowser() throws Throwable {
    	//Create a new instance of the chrome driver
    			String driverPath = System.getProperty("user.dir")
    					+ File.separator
    					+ "drivers"
    					+ File.separator
    					+ "chromedriver.exe";
    			
    			System.setProperty("webdriver.chrome.driver", driverPath);
    			driver = new ChromeDriver();
    			wait = new WebDriverWait(driver, 60);
    }
    
    @Given("^User opens the Job Board Login Page$")
    public void OpenWebsite() throws Throwable {
    //Open the URL
    driver.get("https://alchemy.hguy.co/jobs/wp-admin");
    driver.manage().window().maximize();
    Thread.sleep(5000);
    }
    
    @When("^User logs in with \"(.*)\" and \"(.*)\"$")
    public void loginToJobBard(String username, String password) throws Throwable {
    driver.findElement(By.id("user_login")).sendKeys(username);
    driver.findElement(By.id("user_pass")).sendKeys(password);
    driver.findElement(By.id("wp-submit")).click();
    }
    
    @Then("^Create a new User with providing necessary details on clicking on Users>>Add New button$")
    public void createANewUser() throws Throwable {
    	driver.findElement(By.cssSelector("a.menu-icon-users > div:nth-child(3)")).click();
    	driver.findElement(By.cssSelector(".page-title-action")).click();
    	driver.findElement(By.id("user_login")).sendKeys("preferred");
    	driver.findElement(By.id("email")).sendKeys("testpreferred@gmail.com");
    	driver.findElement(By.id("first_name")).sendKeys("test");
    	driver.findElement(By.id("last_name")).sendKeys("mia");
    	Thread.sleep(5000);
    	driver.findElement(By.cssSelector(".wp-generate-pw")).click();
    	Thread.sleep(5000);
    	driver.findElement(By.id("createusersub")).click();
    }
    
    @And("^Validate that the user has been created$")
    public void validateUserCreation() throws Throwable {
    	String message = driver.findElement(By.cssSelector("#message")).getText();
    	System.out.println(message);
    	Assert.assertTrue(message.contains("New user created."));
    	driver.findElement(By.id("user-search-input")).sendKeys("preferred");
    	driver.findElement(By.id("search-submit")).click();
    	WebElement userName = driver.findElement(By.cssSelector(".username > strong:nth-child(2) > a:nth-child(1)"));
    	Assert.assertEquals(userName.getText(), "preferred");
    } 
    @And ("^Close the Browser$")
    public void closeBrowser() throws Throwable {
    	driver.quit();
    }
    
    @Given("^User opens the Job Board Page$")
    public void openJobPortal() throws Throwable {
    	driver.get("https://alchemy.hguy.co/jobs/");
    	driver.manage().window().maximize();
        
    }
    
    @And("^Navigate to the Job Page$")
    public void ClickJob() throws Throwable {
    	Thread.sleep(5000);
    	driver.findElement(By.cssSelector("#menu-item-24 > a:nth-child(1)")).click();
    }
    
    @When("^Find the Keyword and Type in keywords to search for jobs and change the Job type$")
    public void findJob() throws Throwable {
    	driver.findElement(By.id("search_keywords")).sendKeys("Automation Tester");
    	Thread.sleep(5000);
    	driver.findElement(By.xpath("//*[@id=\"job_type_freelance\"]")).click();
    	Thread.sleep(5000);
    	driver.findElement(By.xpath("//*[@id=\"job_type_freelance\"]")).click();
    }
    @Then("^Find the filter using XPath and filter job type to show only Full Time jobs$")
    public void filterJob() throws Throwable {
    	Thread.sleep(5000);
    	driver.findElement(By.xpath("//*[@id=\"job_type_freelance\"]")).click();
    	Thread.sleep(5000);
    	driver.findElement(By.xpath("//*[@id=\"job_type_internship\"]")).click();
    	Thread.sleep(5000);
    	driver.findElement(By.xpath("//*[@id=\"job_type_part-time\"]")).click();
    	Thread.sleep(5000);
    	driver.findElement(By.xpath("//*[@id=\"job_type_temporary\"]")).click();
    }
    @And("^Find a job listing using XPath and it to see job details$")
    public void SelectAJob() throws Throwable {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/main/article/div/div/ul/li[1]/a/div[1]/h3")));
    	driver.findElement(By.xpath("/html/body/div/div/div/div/main/article/div/div/ul/li[1]/a/div[1]/h3")).click();    	
    }
    @And("^Find the title of the job listing using XPath and print it to the console$")
    public void JobTitle() throws Throwable {
    	String Title= driver.findElement(By.xpath("/html/body/div/div/div/div/main/article/div/header/div/h1")).getText();
    	System.out.println(Title);
    }
    @And("^Find and Click on the Apply for job button$")
    public void applyForJob() throws Throwable {
    driver.findElement(By.cssSelector(".application_button"));
    }
    @And("^Navigate to the Post Job Page$")
    public void postJob() throws Throwable {
    	driver.findElement(By.cssSelector("#menu-item-26 > a:nth-child(1)")).click();
    }
    
    @When("^User provides the information \"(.*)\" , \"(.*)\" , \"(.*)\" , \"(.*)\" , \"(.*)\" and \"(.*)\"$")
    public void filltheJob(String eMail, String title, String location, String jobDescription, String companyURL, String companyName) throws Throwable {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create_account_email")));
    driver.findElement(By.id("create_account_email")).sendKeys(eMail);
    driver.findElement(By.id("job_title")).sendKeys(title);
    driver.findElement(By.id("job_location")).sendKeys(location);
    Thread.sleep(5000);
    driver.findElement(By.xpath("//*[@id=\"job_description_ifr\"]")).sendKeys(Keys.ENTER, jobDescription );
    driver.findElement(By.id("application")).sendKeys(companyURL);
    driver.findElement(By.id("company_name")).sendKeys(companyName);
    driver.findElement(By.cssSelector("input.button:nth-child(4)")).click();
    }
    
    @Then("^Click submit$")
    public void submitJob() throws Throwable {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("job_preview_submit_button")));
    driver.findElement(By.id("job_preview_submit_button")).click();
    }
    
    @And("^Go to the Jobs page$")
    public void navigateToJob() throws Throwable {
    	driver.findElement(By.cssSelector("#menu-item-24 > a:nth-child(1)")).click();
    }
    @And("^Confirm job listing is shown on page with Job Name and Location as \"(.*)\" and \"(.*)\"$")
    public void searchTheJob(String jobName, String location) throws Throwable {
    	driver.findElement(By.cssSelector("#search_keywords")).sendKeys(jobName);
    	driver.findElement(By.cssSelector("#search_location")).sendKeys(location);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div/main/article/div/div/ul/li[1]/a/div[1]/h3")));
    	driver.findElement(By.xpath("/html/body/div/div/div/div/main/article/div/div/ul/li[1]/a/div[1]/h3")).click();
    	String findJob= driver.findElement(By.xpath("/html/body/div/div/div/div/main/article/div/header/div/h1")).getText();
    	Assert.assertEquals(findJob, jobName);
    }

}
