Feature: GOOGLE-DOC|ACTIVITY event validations
  @GOOGLE-DOC
  Scenario Outline: Mindtap users Calculate the Total GOOGLE-DOC|ACTIVITY event in Analytics Tool
    Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
    Then I calculate total events generated for <EventAction> Events under <EventCategory> Event Category

    Examples:
      | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction | ISBN          | EventSource |
      | alok_inst@swlearning.com | course    | course-key     | MTPP0JBPP0DD | reverse | ACTIVITY      | LAUNCH      | 9781133354215 | GTM         |

  @GOOGLE-DOC
  Scenario Outline: Mindtap users performs a GOOGLE-DOC|ACTIVITY Launch
    Given I Launch the snapshot with <username> and <password> into <course> of <ISBN> ISBN
    Then I navigate to unit view tab
    And I select <Unit> unit
    And I extract events details from resource interaction
    Examples:
      | username                 | password  | course                          | Unit                                | Environment  | ISBN          |
      | alok_inst@swlearning.com | Password1 |  MTPP0JBPP0DD                   | GOOGLE-DOCSACTIVITY                 | int          | 9781133354215 |

  @GOOGLE-DOC
  Scenario Outline: Mindtap users Verify the GOOGLE-DOC|ACTIVITY event in Analytics Tool
    Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
    And I verify events generated for <EventAction> Events under <EventCategory> Event Category
    And I verify The Events Details like <EventSource> , <EventCategory> , <value>


    Examples:
      | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction | ISBN          | EventSource | value |
      | alok_inst@swlearning.com | course    | course-key     | MTPP0JBPP0DD | reverse | ACTIVITY      | LAUNCH      | 9781133354215 | GTM         | 1     |

