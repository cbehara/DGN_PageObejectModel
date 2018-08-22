# qetests_dgn
The repo for the DGN automation project.

## How to compile the UI_Core framework:
	Pre-conditon : UI_Core_framework project should be successfully build before compiling the DGN automation project.
	
	1. Open command prompt and go to the DGN automation project directory where the pom.xml file exist.
	2. Execute the command "mvn clean compile" to the project and download all the required dependencies .jar files or "mvn clean install" : Which will download all the required .jar file listed as dependencies as well as start the execution of the test which is provided the default listed testng.xml file.
	
## Pre-conditon to run test on BrowserStack.
	1. Flag "execute.test.parallel=true" should be set under file config.properties.
	2. If execution report are need to save in mongo dp then flag "extentxReports=true" need to set present in framework-config.properties file of UI_Core_framework project.
	3. Need to change/Set the browser/version/OS combination before running the test on the browser stack.
	4. browser/version/OS can be set under file os-browser-platform.json. 

## Dependencies used information.

	1. ui-auto-core : The code framework dependencies is added to pull entire framework into DGN automation project.
	
	2. selenium-server : This dependencies download all the related .jar file required for creating selenium scripts.
	
	3. testng : This dependencies download TestNG library to use all the testng defined methods.
	
	4. surefire-testng : This plug in is required to call the testng.xml file from pom.xml file.
	
	5. log4j : This dependencies is used to create log which we had used to describe each steps of operation.
	
	6. javax.mail-api : This dependencies is used to shoot a mail via a code to configure email id which facilitiy we had provided in our core framework.
	
	7. json-simple :  This dependencies is used to used the JSON which we had used to browser stack required configuration.
	
	8. commons-lang : This dependencies is used to check String related verification which provide the StringUtil function to check String is not empty.
	
	9. browserstack-local-java :  This dependencies is used to create a tunnel to access all the Dodge application from browser stack cloud.
	
	10. automate-testassist : This dependencies is used to embed browser stack reports in Jenkins. 