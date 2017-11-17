Feature: EngagementReports - Request Response  (CAP-185,186,187)

  @CAP-185
  Scenario Outline: Validate status code and response if request hits with instructor not enrolled in the course
    Given I construct a request with <Host> and following parameters : <UP1>,<UP2>,<UP3>,<UP4>,<UP5>
    And I fetch SSO token for the <username> with <password>
    And I hit a GET request with following headers: <H1>,<H2>
    When I see 200 status code
    Then I should see <errormessage> in response body

    Examples:
      | username                 | password  | Host                                           | UP1     | UP2        | UP3    | UP4                                                                          | UP5        | H1                  | H2                | errormessage      |
      | alok_inst@swlearning.com | Password1 | w4qq9qcawk.execute-api.us-east-1.amazonaws.com | staging | engagement | report | mindtap:qad-ng.cengage.com:course:isbn:9781285000848:course-key:MTPNCWXP3RS1 | instructor | environment-context | cengage-sso-token | not an instructor |

  @CAP-186
  Scenario Outline: Validate status code and response if request hits with no students enrolled in the course
    Given I construct a request with <Host> and following parameters : <UP1>,<UP2>,<UP3>,<UP4>,<UP5>
    And I fetch SSO token for the <username> with <password>
    And I hit a GET request with following headers: <H1>,<H2>
    When I see 200 status code
    Then I should see <errormessage> in response body

    Examples:
      | username                 | password  | Host                                           | UP1     | UP2        | UP3    | UP4                                                                          | UP5        | H1                  | H2                | errormessage           |
      | alok_inst@swlearning.com | Password1 | w4qq9qcawk.execute-api.us-east-1.amazonaws.com | staging | engagement | report | mindtap:qad-ng.cengage.com:course:isbn:9781285000848:course-key:MTPQPXCPP93P | instructor | environment-context | cengage-sso-token | MissingReportException |


  @CAP-187
  Scenario Outline: Validate status code and response if request hits with neither resource interactions nor user course scores have been pushed for any student enrolled in the course
    Given I construct a request with <Host> and following parameters : <UP1>,<UP2>,<UP3>,<UP4>,<UP5>
    And I fetch SSO token for the <username> with <password>
    And I hit a GET request with following headers: <H1>,<H2>
    When I see 200 status code
    Then I should see <errormessage> in response body

    Examples:
      | username                 | password  | Host                                           | UP1     | UP2        | UP3    | UP4                                                                          | UP5     | H1                  | H2                | errormessage           |
      | alok_inst@swlearning.com | Password1 | w4qq9qcawk.execute-api.us-east-1.amazonaws.com | staging | engagement | report | mindtap:qad-ng.cengage.com:course:isbn:9781285000848:course-key:MTPNX6DPQGMN | student | environment-context | cengage-sso-token | MissingReportException |

  @CAP-182
  Scenario Outline: Validate status code and response if request hits with no students enrolled in the course
    Given I construct a request with <Host> and following parameters : <UP1>,<UP2>,<UP3>,<UP4>,<UP5>
    And I fetch SSO token for the <username> with <password>
    And I hit a GET request with following headers: <H1>,<H2>
    When I see 200 status code
    Then I should see <errormessage> in response body

#MTPQPXCPP9xx is an Invalid course
    Examples:
      | username                 | password  | Host                                           | UP1     | UP2        | UP3    | UP4                                                                          | UP5        | H1                  | H2                | errormessage           |
      | alok_inst@swlearning.com | Password1 | w4qq9qcawk.execute-api.us-east-1.amazonaws.com | staging | engagement | report | mindtap:qad-ng.cengage.com:course:isbn:9781285000848:course-key:MTPQPXCPP9xx | instructor | environment-context | cengage-sso-token | MissingReportException |

  @CAP-184
  Scenario Outline: Validate status code and response if request hits with instructor not enrolled in the course
    Given I construct a request with <Host> and following parameters : <UP1>,<UP2>,<UP3>,<UP4>,<UP5>
    And I fetch SSO token for the <username> with <password>
    And I hit a GET request with following headers: <H1>,<H2>
    When I see 200 status code
    Then I should see <errormessage> in response body

    Examples:
      | username                 | password  | Host                                           | UP1     | UP2        | UP3    | UP4                                                                          | UP5        | H1                  | H2                | errormessage      |
      | alok_inst@swlearning.com | Password | w4qq9qcawk.execute-api.us-east-1.amazonaws.com | staging | engagement | report | mindtap:qad-ng.cengage.com:course:isbn:9781285000848:course-key:MTPNCWXP3RS1 | instructor | environment-context | cengage-sso-token | Sso authentication failed |


  @CAP-189
  Scenario Outline: Validate status code and response if request hits with instructor not enrolled in the course
    Given I construct a request with <Host> and following parameters : <UP1>,<UP2>,<UP3>,<UP4>,<UP5>
    And I fetch SSO token for the <username> with <password>
    And I hit a GET request with following headers: <H1>,<H2>
    When I see 200 status code
    Then I should see <UP4> in response body

    Examples:
      | username                 | password  | Host                                           | UP1     | UP2        | UP3    | UP4                                                                          | UP5        | H1                  | H2                |
      | alok_inst@swlearning.com | Password1 | w4qq9qcawk.execute-api.us-east-1.amazonaws.com | staging | engagement | report |mindtap:mindtap-int.cengage.com:course:isbn:9781133354215:course-key:MTPPJVMPNSZM | instructor | environment-context | cengage-sso-token |

  @CAP-188
  Scenario Outline: Validate status code and response if request hits with instructor not enrolled in the course
    Given I construct a request with <Host> and following parameters : <UP1>,<UP2>,<UP3>,<UP4>,<UP5>
    Given As a user, I Get SSO Token for <User> from ADT
    And I hit a GET request with following headers: <H1>,<H2>
    When I see 200 status code
    Then I should see <MESSAGE> in response body

    Examples:
      | User                 |  Host                                           | UP1     | UP2        | UP3    | UP4                                                                          | UP5        | H1                  | H2                | MESSAGE|
      | 651d1b13e44c13f9:-eda1fb4:15a3dd9c2bc:-177 |  w4qq9qcawk.execute-api.us-east-1.amazonaws.com | staging | engagement | report | mindtap:qa-automation:staging:course:cfe366d8-109d-420d-97bb-ff6251a79b3a | instructor | environment-context | cengage-sso-token | field |

  @CAP-116
  Scenario Outline: ER Schema Matching
    Given I construct a request with <Host> and following parameters : <UP1>,<UP2>,<UP3>,<UP4>,<UP5>
    Given As a user, I Get SSO Token for <User> from ADT
    And I hit a GET request with following headers: <H1>,<H2>
    When I see 200 status code
    Then I should see engagement report json in response body same as in <SCHEMA> file

    Examples:
      | User                 |  Host                                           | UP1     | UP2        | UP3    | UP4                                                                          | UP5        | H1                  | H2                | SCHEMA|
      | 651d1b13e44c13f9:-eda1fb4:15a3dd9c2bc:-177 |  w4qq9qcawk.execute-api.us-east-1.amazonaws.com | staging | engagement | report | mindtap:qa-automation:staging:course:6a5df8b8-c509-44de-ab51-eb202d08da70 | instructor | environment-context | cengage-sso-token | engagement_report_schema.json |
      