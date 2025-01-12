#Author: vinita sharma
#Keywords Summary : 

Feature: Add item to the cart 
  I want to use this template for my feature file

  @addItem @functional-test
  Scenario: Verify item can be added to Cart
    Given User launch application url https://www.ebay.com/
    Then User verifies Page Title as Electronics, Cars, Fashion, Collectibles & More | eBay
    And User check for item number in cart
    When User search for book in item search box
    And User clicks searched item number 1 from list
    Then User switch to item window
    And User click on Add to cart button
    Then User verifies item count in cart is increased by 1    