Feature: Quiz : AA(ACTIVITY) event validations
  @CAP-134
  Scenario Outline: Mindtap users Calculate the Total Quiz : AA(ACTIVITY) event in Analytics Tool
    Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
    Then I calculate total events generated for <EventAction> Events under <EventCategory> Event Category

    Examples:
      | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction | ISBN          | EventSource |
      | alok_inst@swlearning.com | course    | course-key     | MTPPFJ5PNJ5L | reverse | ACTIVITY    | LAUNCH      | 9781133999393 | GTM         |

  @CAP-134
  Scenario Outline: Mindtap users performs a Quiz : AA(ACTIVITY) event
    Given I Launch the snapshot with <username> and <password> into <course> of <ISBN> ISBN
    Then I navigate to unit view tab
    And I select <Unit> unit
    And I extract events details from resource interaction
    Examples:
      | username                 | password  | course                          | Unit                 | Environment | ISBN |
      | alok_inst@swlearning.com | Password1 |  MTPPFJ5PNJ5L    | Quiz : AA        | int          | 9781133999393 |

  @CAP-134
  Scenario Outline: Mindtap users Verify the Quiz : AA(ACTIVITY) event in Analytics Tool
    Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
    And I verify events generated for <EventAction> Events under <EventCategory> Event Category
    And I verify The Events Details like <EventSource> , <EventCategory> , <value>
    
    Examples:
      | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction | ISBN          | EventSource | value |
      | alok_inst@swlearning.com | course    | course-key     | MTPPFJ5PNJ5L | reverse | ACTIVITY    | LAUNCH      | 9781133999393 | GTM         | 1     |

