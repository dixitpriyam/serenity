
Feature: SEARCH-PERFORMED event validations
  @CAP-121
  Scenario Outline: Mindtap users Calculate the Total SEARCH-PERFORMED event in Analytics Tool
    Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
    Then I calculate total events generated for <EventAction> Events under <EventCategory> Event Category

    Examples:
      | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction | ISBN          | EventSource |
      | alok_inst@swlearning.com | course    | course-key     | MTPPR1GPNWJG | reverse | SEARCH    | SEARCH-PERFORMED      | 9781133354215 | GTM         |

  @CAP-121
  Scenario Outline: Mindtap users performs a SEARCH-PERFORMED event
    Given I Launch the snapshot with <username> and <password> into <course> of <ISBN> ISBN
    When I search a page with <page_no>
    And I extract events details from resource interaction
    Examples:
      | username                 | password  | course         | page_no              |ISBN|
      | alok_inst@swlearning.com | Password1 | MTPPR1GPNWJG   | 22                   | 9781133354215|

  @CAP-121
  Scenario Outline: Mindtap users Verify the SEARCH-PERFORMED event in Analytics Tool
    Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
    And I verify events generated for <EventAction> Events under <EventCategory> Event Category
    And I verify The Events Details like <EventSource> , <EventCategory> , <value>

    Examples:
      | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction | ISBN          | EventSource | value |
      | alok_inst@swlearning.com | course    | course-key     | MTPPR1GPNWJG | reverse | SEARCH    | SEARCH-PERFORMED      | 9781133354215 | GTM         | 1     |


