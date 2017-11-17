Feature: WEBLINKS|ACTIVITY event validations
  @CAP-169
  Scenario Outline: Mindtap users Calculate the Total WEBLINKS|ACTIVITY event in Analytics Tool
    Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
    Then I calculate total events generated for <EventAction> Events under <EventCategory> Event Category

    Examples:
      | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction | ISBN          | EventSource |
      | alok_inst@swlearning.com | course    | course-key     | MTPNC2HPPMZ6 | reverse | ACTIVITY    | LAUNCH      | 9781133354215 | GTM         |

  @CAP-169
  Scenario Outline: Mindtap users performs a WEBLINKS|ACTIVITY Launch
    Given I Launch the snapshot with <username> and <password> into <course> of <ISBN> ISBN
    Then I navigate to unit view tab
    And I select <Unit> unit
    And I extract events details from resource interaction
    Examples:
      | username                 | password  | course                          | Unit                 | Environment | ISBN |
      | alok_inst@swlearning.com | Password1 |  MTPNC2HPPMZ6    | WEBLINKSACTIVITY event         | int          | 9781133354215 |

  @CAP-169
  Scenario Outline: Mindtap users Verify the WEBLINKS|ACTIVITY event in Analytics Tool
    Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
    And I verify events generated for <EventAction> Events under <EventCategory> Event Category
    And I verify The Events Details like <EventSource> , <EventCategory> , <value>

    Examples:
      | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction | ISBN          | EventSource | value |
      | alok_inst@swlearning.com | course    | course-key     | MTPNC2HPPMZ6 | reverse | ACTIVITY    | LAUNCH      | 9781133354215 | GTM         | 1     |


