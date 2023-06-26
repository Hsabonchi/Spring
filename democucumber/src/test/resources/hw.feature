Feature: this is the test case to test spring functionalities
Scenario: to test uppercase
  Given this is the data "HelloWorld"
  When uppercase is called
  Then output is "HELLOWORLD"
  Scenario: to test lowercase
    Given this is the data for lowercase "HELLOWORLD"
    When lowercase is called
    Then output for lowercase is "helloworld"
