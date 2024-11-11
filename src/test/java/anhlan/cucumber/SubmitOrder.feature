@tag
Feature: Purchase the order from Ecommerce Website
  Background:
  Given I landed on Ecommerce page

  @tag2
  Scenario Outline: Positive test of submitting order
    Given Log in with username <name> and password <pass>
    When I add the product <product> from Cart
    When Checkout <product> and submit the order
    Then "Thankyou for the order." success message is displayed
    Examples:
     | name                        |   pass         | product         |
     | anhlan.selenium@email.com   |   Anh.lan@123  | ADIDAS ORIGINAL |
