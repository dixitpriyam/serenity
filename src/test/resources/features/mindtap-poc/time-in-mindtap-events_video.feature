# Created by adithya at 14-03-2017
Feature: Time-in-mindtap events


  @CAP-177
  Scenario Outline: ADT user retrieves event counts
    Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
    Then I calculate total time-in-tap events generated for <EventAction> Events under <EventCategory> Event Category

    Examples:
      | Search_Data                   | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory   | EventAction | ISBN          | EventSource |
      | analyticstimeinmindtap@sw.com | course    | course-key     | MTPP6DMPPSM6 | reverse | TIME-IN-MINDTAP | UNFOCUSED   | 9781133354215 | GTM         |

  @CAP-177
  Scenario Outline: Mindtap users spent time in mindtap page
    Given I Launch the snapshot with <username> and <password> into <course> of <ISBN> ISBN
    Then I navigate to unit view tab
    And I select <Unit> unit
    And I select <Activity> reading activity
    And I Play the Video
    And I extract events details from resource interaction

    Examples:
      | username                      | password | course       | Unit               | Activity       | ISBN          |
      | AnalyticsTimeinmindtap@sw.com | Test123  | MTPP6DMPPSM6 | Added by Master        | Media Activity     | 9781133354215 |

  @CAP-177
  Scenario Outline: ADT user retrieves event counts
    Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
    And I verify time in mindtap events generated for <EventAction> Events under <EventCategory> Event Category
    And I verify The Events Details like <EventSource> , <EventCategory> , <value>

    Examples:
      | Search_Data                   | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory   | EventAction | ISBN          | EventSource | value |
      | analyticstimeinmindtap@sw.com | course    | course-key     | MTPP6DMPPSM6 | reverse | TIME-IN-MINDTAP | UNFOCUSED   | 9781133354215 | GTM         | 60    |


