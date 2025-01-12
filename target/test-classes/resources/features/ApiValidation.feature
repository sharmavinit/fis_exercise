#Author: vinita sharma
#Keywords Summary: This feature executes get api and validates given field values in response
 @api-validation @test-exercise
Feature: Execute Get request and validate response fields

  Scenario: Execute Get api request and validate BPIs and GBI BPI description
  	Given User has API endpoint as https://api.coindesk.com/v1/bpi/currentprice.json
    When User executes GET request for the given API and verifies status code as 200
    Then User validates that bpi fields in response should contain the below values:
      | USD |
      | GBP |
      | EUR |
    And User validates value of bpi.GBP.description field in response as British Pound Sterling
  
