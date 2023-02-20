Feature: Get Tests on calculator
  Background:
  * def urlBase = 'http://localhost:8080/api/findFactorial/7'
  Scenario: Get findFactorial
    Given url urlBase
    When method GET
    Then status 200
