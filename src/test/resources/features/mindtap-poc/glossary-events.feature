# Created by adithya at 14-03-2017
  Feature: Glossary Events


    @CAP-154
    Scenario Outline: ADT user retrieves event counts
    Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
    Then I calculate total events generated for <EventAction> Events under <EventCategory> Event Category

    Examples:
    | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction   | ISBN          | EventSource |
    | alok_inst@swlearning.com | course    | course-key     | MTPPFRCPNJBW | reverse | GLOSSARY      | LAUNCH        | 9781133353706 | GTM         |
#    | alok_inst@swlearning.com | course    | course-key     | MTPPFRCPNJBW | reverse | GLOSSARY      | GLOSSARY-SHOW | 9781133353706 | GTM         |

    @CAP-154
    Scenario Outline: Mindtap users performs a Glossary Launch event
    Given I Launch the snapshot with <username> and <password> into <course> of <ISBN> ISBN
    When I select Glossary app from app dock
    Then I should see the glossary content
    And I extract events details from resource interaction

    Examples:
    | username                 | password  | course              |ISBN|
    | alok_inst@swlearning.com | Password1 | MTPPFRCPNJBW | 9781133353706|

#    @CAP-154
#    Scenario Outline: Mintap user performs Glossary Show event
#    Given I am on course page
#    When I select a <chapter> from <view>
#    Then I should see the definition of glossary term <glossary item> in the chapter contents
##    And I extract events details from resource interaction
#    Examples:
#    | chapter | view      | glossary item   |
#    | 1:      | Unit View | City of the Sun |


    @CAP-154
    Scenario Outline: ADT user retrieves event counts
    Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
    And I verify events generated for <EventAction> Events under <EventCategory> Event Category
    And I verify The Events Details like <EventSource> , <EventCategory> , <value>

    Examples:
    | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction   | ISBN          | EventSource | value |
    | alok_inst@swlearning.com | course    | course-key     | MTPPFRCPNJBW | reverse | GLOSSARY      | LAUNCH        | 9781133353706 | GTM         | 1     |
#    | alok_inst@swlearning.com | course    | course-key     | MTPPFRCPNJBW | reverse | GLOSSARY      | GLOSSARY-SHOW | 9781133353706 | GTM         | 1     |
