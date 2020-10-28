@jobAlchemy
Feature: Testing the Job Board application

Background: User opens the URL
	Given User opens the Bowser

@TestCaseOne
Scenario: Create a new user
Scenario Outline: Visit the site’s backend and create a new user
    Given User opens the Job Board Login Page
    When User logs in with "root" and "pa$$w0rd"
    Then Create a new User with providing necessary details on clicking on Users>>Add New button
    And Validate that the user has been created
    And Close the Browser
    
@TestCaseTwo
Scenario: Searching for jobs using XPath
Scenario Outline: Searching for jobs and applying to them using XPath
    Given User opens the Job Board Page
    And Navigate to the Job Page
    When Find the Keyword and Type in keywords to search for jobs and change the Job type
    Then Find the filter using XPath and filter job type to show only Full Time jobs
    And Find a job listing using XPath and it to see job details
    And Find the title of the job listing using XPath and print it to the console
    And Find and Click on the Apply for job button
    And Close the Browser
    	
@TestCaseThree
Scenario: Posting a job using parameterization
Scenario Outline: Post a job using details passed from the Feature file
    Given User opens the Job Board Page
    And Navigate to the Post Job Page
    When User provides the information "testmia@gmail.com" , "SDET Tester" , "Bangalore" , "Need a tester with knowledge of JAVA, Python, Selenium, TestNG, ReportNG, Cucumber, Jira, Zypher and Manual Testing" , "testmia@gmail.com" and "IBM India Pvt Ltd"  
    Then Click submit
    And Go to the Jobs page
    And Confirm job listing is shown on page with Job Name and Location as "SDET Tester" and "Delhi NCR"
    And Close the Browser
    
@TestCaseFour
Scenario: Posting a job using parameterization
Scenario Outline: Post a job using details passed from the Feature file
    Given User opens the Job Board Page
    And Navigate to the Post Job Page
    When User provides the information "<eMail>" , "<title>" , "<location>" , "<jobDescription>" , "<companyURL>" and "<companyName>"
    Then Click submit
    And Go to the Jobs page
    And Confirm job listing is shown on page with Job Name and Location as "SDET Developer Tester" and "Gurgaon"
    And Close the Browser
    
 Examples:
|eMail|title|location|jobDescription|companyURL|companyName|
|testlilly@gmail.com|SDET Tester|Bangalore|Need a tester with knowledge of JAVA, Python, Selenium, TestNG, ReportNG, Cucumber, Jira, Zypher and Manual Testing|testmia@gmail.com|IBM India Pvt Ltd|
