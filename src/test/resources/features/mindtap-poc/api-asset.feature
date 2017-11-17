Feature: WebServices - POST validation

#  Scenario Outline: Validate status code if request hits with SSL
#    Given I construct a request with <Host> and following parameters : <UP1>,<UP2>
#    When I hit a POST request with <Body> and following headers: <H1>,<H2>
#    Then I see 200 status code
#    Examples:
#      | Host                                           | UP1     | UP2                   | Body                   | H1        | H2           |
#      | u4k8v1b3s8.execute-api.us-east-1.amazonaws.com | staging | resource-interactions | search_event_body.json | x-api-key | Content-Type |
#

