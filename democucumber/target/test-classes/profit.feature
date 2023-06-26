Feature: Calculate profit or loss
  Scenario Outline: Calculate profit  <sp> & <mrp>
    Given I have a profit calculator
    When I add <sp> and <mrp>
    Then the result should be <result>
    Examples:
      | sp | mrp | result |
      | 100.0 | 30.0 | "PROFIT" |
      | 10.0  | 15.0 | "LOSS"   |