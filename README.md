### WebWolf - A WEB UI Test Automation Framework

#### Tech Stack

|                                           |                                                                                                    |
|-------------------------------------------|----------------------------------------------------------------------------------------------------|
| Programming Language                      | <a href="https://www.oracle.com/in/java/technologies/javase/jdk11-archive-downloads.html">Java</a> |
| Build and Library management              | <a href="https://maven.apache.org/">Maven</a>                                                      |
| WEB automation Library                    | Selenium     , Selenide                                                                            |
| HTTP Library                              | Rest Assured                                                                                       |
| Assertion Library                         | AssertJ                                                                                            |
| Test runner  Library                      | TestNG                                                                                             |
| Reporting  Library                        | Extent Report                                                                                      |
| Logging  Library                          | Log4J                                                                                              |
| CI/CD                                     | Jenkins, Github Actions                                                                            |
| Optimize boilerplate code                 | Lombok                                                                                             |
| Async library                             | Awaitility                                                                                         |
| Serialization and Deserialization library | Jackson                                                                                            |
| Custom exception handler                  | NoException                                                                                        |
| Secret manager                            | gitcrypt                                                                                           |
| Test data generator                       | javafaker                                                                                          |
| Configuration manager                     | owner                                                                                              |

#### Framework features

- Automate Web UI actions in various browsers like chrome,edge, firefox, safari
- Supports multiple test environments like dev, test, stage
- Easily manage configurations for webdriver, browser, environment variables
- Supports parallel execution of tests
- Generates logs and html report
- Integrate with Ci/CD like jenkins/Github actions
- Secrete management
- Automatic Code formatting
- Page object model for easier management of pages and test
-

#### Best practices to write Tests
- Follow a naming convention for test
  - Should {do something} if {given a state or performed an action}
- Use TODO with comment if you have anything pending to do in a later stage
- Make sure to add annotation to each test or test class based on their category
  - Example: 
    - Login feature tests , we can mark class  with @LoginTest
    - If it is specific to a test we can mark annotation to individual test too
  - To run via commandline <b>  ``` mvn clean test -Dgroups=login``` or ``` mvn clean test -Dgroups={"login","inventory"}```
#### References
- TBD
#### Credits

- Inspired and Thankful to amazing friends/colleagues/mentors
    - Testing Mini Bytes [Youtube channel](https://www.youtube.com/@TestingMiniBytes) ( Amuthan Saktivel )
    - Power Tester [Youtube channel](https://www.youtube.com/@powertester5596) ( Pramod Yadav )
