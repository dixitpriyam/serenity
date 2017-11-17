Feature: System check event (CAP-156)


  @CAP-156
  Scenario Outline: ADT user retrieves event counts
    Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
    Then I calculate total events generated for <EventAction> Events under <EventCategory> Event Category

    Examples:
      | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction | ISBN          | EventSource |
      | alok_inst@swlearning.com | course    | course-key     | MTPP4F4PPXJK | reverse | SYSTEMCHECK   | LAUNCH      | 9781133354215 | GTM         |

  @CAP-156
  Scenario Outline: Mintap user performs System check launch event
    Given I Launch the snapshot with <username> and <password> into <course> of <ISBN> ISBN
    Then I navigate to unit view tab
    When I select System Check from user menu
    Then I should see system requirement pane
    And I extract events details from resource interaction
    Examples:
      | username                 | password  | course          |  ISBN                 |
      | alok_inst@swlearning.com | Password1 | MTPP4F4PPXJK    | 9781133354215         |


  @CAP-156
  Scenario Outline: ADT user retrieves event counts
    Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
    And I verify events generated for <EventAction> Events under <EventCategory> Event Category
    And I verify The Events Details like <EventSource> , <EventCategory> , <value>

    Examples:
      | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction | ISBN          | EventSource | value |
      | alok_inst@swlearning.com | course    | course-key     | MTPP4F4PPXJK | reverse | SYSTEMCHECK   | LAUNCH      | 9781133354215 | GTM         | 1     |