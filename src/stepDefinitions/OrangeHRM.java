package stepDefinitions;

import java.io.File;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrangeHRM {
	
	private WebDriver driver = null;
	private WebDriverWait wait = null;
	
	@Given("^User opens the Browser$")
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
        driver.manage().window().maximize();
    }
	@When("^Open the OrangeHRM page and login with credentials provided \"(.*)\" and \"(.*)\"$")
	public void OpenURLandLogin(String username, String password) throws Throwable {
		driver.get("http://alchemy.hguy.co:8080/orangehrm/symfony/web/index.php/auth/login");
		driver.findElement(By.id("txtUsername")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();	
	}
	@Given("^Navigate to the Recruitment page$")
	public void navigateToRecruitment() throws Throwable {
		Thread.sleep(6000);	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_recruitment_viewRecruitmentModule")));
		driver.findElement(By.id("menu_recruitment_viewRecruitmentModule")).click();
	}
	@When("^Click on the Vacancies menu item to navigate to the vacancies page$")
	public void navigateToVacancies() throws Throwable {
		Thread.sleep(6000);	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_recruitment_viewJobVacancy")));
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();
	}
	@Then("^Click on the Add button to navigate to the Add Job Vacancy form$")
	public void AddButtonToAddJob() throws Throwable{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnAdd")));
		driver.findElement(By.id("btnAdd")).click();
	}
	@And("^Fill out the necessary details$")
	public void fillJobForm() throws Throwable{
		WebElement jobTitle = driver.findElement(By.id("addJobVacancy_jobTitle"));  
		Select dropdown = new Select(jobTitle); 
		dropdown.selectByVisibleText("Automation Test Engineer");
		driver.findElement(By.id("addJobVacancy_name")).sendKeys("SDET Full Stack Tester");
		driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys("saranya ram");
		driver.findElement(By.id("addJobVacancy_noOfPositions")).sendKeys("10");
	}
		
	@And("^Click the Save button to save the vacancy$")
	public void saveJob() throws Throwable{
		driver.findElement(By.id("btnSave")).click();
	}
	@And("^Verify that the vacancy was created$")
	public void validateJob() throws Throwable{
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("vacancySearch_jobTitle")));
		WebElement jobSearch = driver.findElement(By.id("vacancySearch_jobTitle"));  
		Select dropdown1 = new Select(jobSearch); 
		dropdown1.selectByVisibleText("Automation Test Engineer");
		WebElement vacancy = driver.findElement(By.id("vacancySearch_jobVacancy"));  
		Select dropdown2 = new Select(vacancy); 
		dropdown2.selectByVisibleText("SDET Full Stack Tester");
		driver.findElement(By.id("btnSrch")).click();
		String jobTitle= driver.findElement(By.cssSelector("td.left:nth-child(2) > a:nth-child(1)")).getText();
		Assert.assertEquals("SDET Full Stack Tester", jobTitle);
		String status = driver.findElement(By.cssSelector("td.left:nth-child(5)")).getText();
		Assert.assertEquals("Active", status);
	}
	@And("^Close the chrome Browser$")
	public void CloseChromeBrowser() throws Throwable{
		driver.quit();
		
	}
	@When("^Click on the Add button to add candidate information$")
	public void addButtonCandidature() throws Throwable{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnAdd")));
		driver.findElement(By.id("btnAdd")).click();
	}
	@Then("^On the Add Candidate page fill in the details of the candidate$")
	public void candidateForm() throws Throwable{
		Thread.sleep(6000);
		driver.findElement(By.id("addCandidate_firstName")).sendKeys("Ramayan");
		driver.findElement(By.id("addCandidate_lastName")).sendKeys("Nayar");
		driver.findElement(By.id("addCandidate_email")).sendKeys("test4536@gmail.com");
		driver.findElement(By.id("addCandidate_contactNo")).sendKeys("877289899");
		WebElement jobSearch1 = driver.findElement(By.id("addCandidate_vacancy"));  
		Select dropDown2 = new Select(jobSearch1); 
		dropDown2.selectByVisibleText("Automation Test Engineer");
		driver.findElement(By.id("addCandidate_comment")).sendKeys("3 Year Experience, looking for immediate joining ");
		driver.findElement(By.id("addCandidate_consentToKeepData")).click();
	}
		
		@And("^Upload a resume docx or pdf to the form$")
		public void uploadResume() throws Throwable{
			Thread.sleep(5000);
			WebElement uploadElement = driver.findElement(By.id("addCandidate_resume"));
			uploadElement.sendKeys("C:\\Users\\RajatSharma\\eclipse-workspace\\sdetCucumberProjectActivity\\src\\test\\resources\\Resume.pdf");
		}
		@And("^Click Save$")
		public void saveCandidature() throws Throwable{
			driver.findElement(By.id("btnSave")).click();
		}
		@And("^Navigate back to the Recruitments page to confirm candidate entry$")
		public void validateCandidature() throws Throwable{
			driver.findElement(By.id("menu_recruitment_viewCandidates")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("candidateSearch_jobTitle")));
			WebElement jobSearch2 = driver.findElement(By.id("candidateSearch_jobTitle"));  
			Select dropDown3 = new Select(jobSearch2); 
			dropDown3.selectByVisibleText("Automation Test Engineer");
			driver.findElement(By.id("candidateSearch_candidateName")).sendKeys("Ramayan Nayar");
			driver.findElement(By.id("btnSrch")).click();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("tr.odd:nth-child(1) > td:nth-child(3) > a:nth-child(1)")).click();
			Thread.sleep(5000);
			String status= driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[2]/form/fieldset/ol[2]/li[1]/div/div/span")).getText();
			System.out.println(status);
		}
		@Given("^Find the PIM option in the menu and click it$")
		public void clickOnPIM() throws Throwable{
			Thread.sleep(5000);
			driver.findElement(By.id("menu_pim_viewPimModule")).click();
		}
		@When("^Click the Add button to add a new Employee$")
		public void addEmployee() throws Throwable{
			Thread.sleep(5000);
			driver.findElement(By.id("btnAdd")).click();
		}
		@Then("^Create Login Details checkbox is checked$")
		public void checkCreateLoginDetails() throws Throwable{
			Thread.sleep(5000);
			driver.findElement(By.id("chkLogin")).click();
		}
		@And("^Fill in the required fields using the data from the Examples table and click Save \"(.*)\" and \"(.*)\" and \"(.*)\" and \"(.*)\"$")
		public void createEmployee(String fName, String lName, String uName, String password) throws Throwable{
			driver.findElement(By.id("firstName")).sendKeys(fName);
			driver.findElement(By.id("lastName")).sendKeys(lName);
			driver.findElement(By.id("user_name")).sendKeys(uName);
			driver.findElement(By.id("user_password")).sendKeys(password);
			driver.findElement(By.id("re_password")).sendKeys(password);
			Thread.sleep(4000);
			driver.findElement(By.id("btnSave")).click();
		}
		@And("^Verify that the employees have been created with \"(.*)\" and \"(.*)\"$")
		public void validatingEmoloyeeCreation(String fName, String lName) throws Throwable{
			Thread.sleep(5000);
			String Name= driver.findElement(By.cssSelector("#profile-pic > h1:nth-child(1)")).getText();
			Assert.assertEquals(fName + " " + lName, Name);
		}
		@And("^Fill out the necessary details from example table as \"(.*)\" and \"(.*)\" and \"(.*)\" and \"(.*)\" and \"(.*)\"$")
		public void fillJobFormfromExample(String jTitle, String vacName, String hiringManager,String noPosition, String description) throws Throwable{
			Thread.sleep(5000);
			WebElement jobTitle1 = driver.findElement(By.id("addJobVacancy_jobTitle"));  
			Select dropdown = new Select(jobTitle1); 
			dropdown.selectByVisibleText(jTitle);
			driver.findElement(By.id("addJobVacancy_name")).sendKeys(vacName);
			driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys(hiringManager);
			driver.findElement(By.id("addJobVacancy_noOfPositions")).sendKeys(noPosition);
			driver.findElement(By.id("addJobVacancy_description")).sendKeys(description);
		}
		@And("^Verify that the vacancy was created by \"(.*)\" and \"(.*)\"$")
		public void validateJobCreation(String jTitle, String vacName) throws Throwable{
			driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();
			Thread.sleep(5000);
			WebElement jobSearch = driver.findElement(By.id("vacancySearch_jobTitle"));  
			Select dropdown1 = new Select(jobSearch); 
			dropdown1.selectByVisibleText(jTitle);
			Thread.sleep(5000);
			WebElement vacancy = driver.findElement(By.id("vacancySearch_jobVacancy"));  
			Select dropdown2 = new Select(vacancy); 
			dropdown2.selectByVisibleText(vacName);
			driver.findElement(By.id("btnSrch")).click();
			Thread.sleep(4000);
			String jobTitle= driver.findElement(By.cssSelector("td.left:nth-child(2) > a:nth-child(1)")).getText();
			Assert.assertEquals(vacName, jobTitle);
			String status = driver.findElement(By.cssSelector("td.left:nth-child(5)")).getText();
			Assert.assertEquals("Active", status);
		}

}
