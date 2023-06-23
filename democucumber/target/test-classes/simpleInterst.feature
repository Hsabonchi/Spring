Feature: Simple Interest
Scenario Outline: Find Simple Interest <principal> & <roi> &  <noOfYears>
Given I have a Simple interest calculator
When calculate simple interest <principal> <roi> <noOfYears>
Then Simple interest value is <si>

Examples:
| principal | roi | noOfYears | si |
| 1000.0    | 3.0 | 1.0       | 30.0  |
| 2000.0    | 3.0 | 1.0       | 60.0  |