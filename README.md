## About
This is an example of the test automation project with API tests using RestAssured and mobile tests (Android) using 
Selenide (which is a test-automation-friendly wrapper-library around Appium and WebDriver).

## Running tests

This project is using Gradle as a build tool and thus, all commands must start with `./gradlew` on Mac and Linux and 
`gradlew.bat` on Windows.
To be able to run all the tests, the following requirements must be met:
1. Run an Android emulator.
2. Run the Appium server.
Refer to the official [Appium documentation](https://appium.io/docs/en/2.3/quickstart/) for getting help on both
requirements.

The following command will run all tests in the project:
```shell
./gradlew test
```

### Running API tests only
API test classes have the `api-tests` JUnit's tag to be able to run tests against API only and exclude any other tests.
Run the following command:

```shell
./gradlew test -DincludeTags=api-tests
```

**NB!** The `shouldDeleteExistingUser()` test is made to fail on purpose. That is done to be able to see a failing 
test case in the report.

### Running mobile tests only
Mobile tests against the Android platform have the `android-tests` JUnit's tag. Run the following command (make sure
to have both an Android emulator and Appium server running):

```shell
./gradlew test -DincludeTags=android-tests
```

**NB!** The `whenUserOpensLogInPage_thenUserCanSeeForgotYourPasswordLink()` scenario is made to fail on purpose with
the same reason as mentioned above in the API section.

## View test-results in the report
This project has an integration with the [Allure Report](https://allurereport.org/), which is a very convenient and 
efficient reporting tool.
Once tests have been run using the above command, run the following command to view the report. It will be opened 
automatically in the default browser.

```shell
./gradlew allureServe
```

## Final notes

For demo purposes, two omissions are made in this project that are done differently in a real commercial projects.

First, the API token is stored as a plain text in the properties file under test resources. This is not how it should 
be done. In a real project, all credentials and tokens are usually stored in a secret vault of the build server and 
retrieved from there during the test execution. No credentials should be checked in the version control.

Second, the APK file of the Android application under test is checked in version control and is stored under the test
resources. In a non-demo project, all the binary artifacts are stored in the binary repository and should be downloaded
from that repository during the test execution.
