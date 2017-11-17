# Created by Adithya at 13-04-2017
Feature: Time-in-mindtap-Aplia course(CAP-175)

  @CAP-175
  Scenario Outline: ADT user retrieves event counts
    Given As a user, I launch ADT URL for staging environment with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameters for timeinmindtap <EventCategory>,<EventAction>,<EventSource>
    Then I calculate total time-in-tap events generated for <EventAction> Events under <EventCategory> Event Category

    Examples:
      | Search_Data                     | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory   | EventAction | ISBN          | EventSource |
      | inst9_automation@swlearning.com | course    | course-key     | MTPN2K2QPTLQ | reverse | TIME-IN-MINDTAP | UNFOCUSED   | 9781285853482 | GTM         |

  @CAP-175
  Scenario Outline: Mindtap users spent time in mindtap page
    Given I Launch the snapshot with <username> and <password> into <course> of <ISBN> ISBN on <Environment>
#    Given As a Mindtap user,I login in staging environment with following <username> and <password>
#    And I launch a <course> on <Environment> Environment
    When I select Aplia app from app dock
    Then I spent <time> in mindtap page
    And I <logout> from mindtap page
    And I extract events details from resource interaction

    Examples:
      | username                        | password | course                 | time | Environment | ISBN          | logout |
      | inst9_automation@swlearning.com | A111111  | MTPN2K2QPTLQ | 120  | staging     | 9781285853482 | Logout |

  @CAP-175
  Scenario Outline: ADT user retrieves event counts
    Given As a user, I launch ADT URL for staging environment with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameters for timeinmindtap <EventCategory>,<EventAction>,<EventSource>
    And I verify time in mindtap events generated for <EventAction> Events under <EventCategory> Event Category
    And I verify The Events Details like <EventSource> , <EventCategory> , <value>

    Examples:
      | Search_Data                     | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory   | EventAction | ISBN          | EventSource | value |
      | inst9_automation@swlearning.com | course    | course-key     | MTPN2K2QPTLQ | reverse | TIME-IN-MINDTAP | UNFOCUSED   | 9781285853482 | GTM         | 60    |

