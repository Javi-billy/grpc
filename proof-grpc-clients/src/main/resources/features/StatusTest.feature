Feature: Get Tests on calculator
  Background:
  * def urlBase = 'http://localhost:8080/api/'
  Scenario Outline: Get <method>
    * def pathParam = '<pathVariable>' == 'null' ? '' : '/<pathVariable>'
    * def requestParam = ''
    * def funRequest = function(i){ return  requestParam += (requestParam == '' ? '?' : '&')  + 'numbers='+ i }
    * def requestBodyParam = '<requestBody>' == 'null' ? '' : karate.forEach(<requestBody>, funRequest)
    Given url urlBase + '<method>' + pathParam + requestParam
    When method GET
    Then status 200
    And match <resultPath> == <result>

    Examples:
      | method          | pathVariable  | requestBody        | result     | resultPath        |
      | findFactorial   | 5             | null               | 120        | $.result          |
      | getAllFactors   | 18            | null               | [2,3,6,9]  | $.[*].result      |
      | sumAll          | null          | [3,7,8,2,1,7,13]   | 41         | $.result          |
      | findPrime       | null          | [3,7,8,54,22,13]   | [3,7,13]   | $.[*].body.result |
