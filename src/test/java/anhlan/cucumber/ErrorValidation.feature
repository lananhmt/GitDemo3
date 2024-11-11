@tag
Feature: Login screen error validation

  @errorValidation
  Scenario Outline: validation username and password
    Given I landed on Ecommerce page
    When Log in with username <name> and password <pass>
    Then "Incorrect email or password." error message is displayed
    Examples:
      | name                        |   pass         |
      | anhlan.selenium@email.com   |   Anhlan       |
      | anhlan.selenium@email.com   |   Anh.lan@122  |

