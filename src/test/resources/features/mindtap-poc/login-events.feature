Feature: LOGIN event validations
  @CAP-130
   Scenario Outline: Mindtap users Calculate the Total LOGIN event in Analytics Tool
    Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
    Then I calculate total events generated for <EventAction> Events under <EventCategory> Event Category

      Examples:
        | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction   | ISBN          | EventSource |
        | alok_inst@swlearning.com | course    | course-key     | MTPN17KPNH10 | reverse | LOGIN      | LOGIN        | 9781133354215 | GTM         |

  @CAP-130
   Scenario Outline: Mindtap users performs a LOGIN event
    Given I Launch the snapshot with <username> and <password> into <course> of <ISBN> ISBN
    Then I navigate to unit view tab
    When I search a page with <page_no>
    And I extract events details from resource interaction
       Examples:
         | username                 | password  | course            | Unit             | Activity          | Section            | Environment  | ISBN         | page_no |
         | alok_inst@swlearning.com | Password1 | MTPN17KPNH10    | CHAPTER 2:       | CHAPTER 2:        | Major Body Cavities           |int  | 9781133354215 |  22 |

  @CAP-130
  Scenario Outline: Mindtap users Verify the LOGIN Create event in Analytics Tool
    Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
    And I verify events generated for <EventAction> Events under <EventCategory> Event Category
    And I verify The Events Details like <EventSource> , <EventCategory> , <value>
    Then I create Json file for <EventAction> Events under <EventCategory> Event Category

       Examples:
         | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction      | ISBN              | EventSource | value |
         | alok_inst@swlearning.com | course    | course-key     | MTPN17KPNH10 | reverse | LOGIN         | LOGIN            | 9781133354215     | GTM         | 1     |


