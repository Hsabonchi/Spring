Feature: Calculate fix rate
  Scenario Outline: Find fix rate  <amount> & <rate>
    Given I have a FxRateTranslator calculator
    When I multiply  <amount> with <rate>
    Then the fix rate value should be <result>
    Examples:
      | amount     | rate | result |
      | 1000.0    | 3.0  |3000.0  |
      | 2000.0    | 3.0  |6000.0  |