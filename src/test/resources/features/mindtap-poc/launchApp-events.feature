Feature: Launch App Events


  @CAP-124
   Scenario Outline: Mindtap users Calculate the Total App Launch event in Analytics Tool
    Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
    Then I calculate total events generated for <EventAction> Events under <EventCategory> Event Category

    Examples:
      | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction | ISBN          | EventSource |
      | alok_inst@swlearning.com | course    | course-key     | MTPPR1GPNWJG | reverse | PROGRESS    | LAUNCH      | 9781133354215 | GTM         |

  @CAP-124
   Scenario Outline: Mindtap users performs a App Launch event
       Given I Launch the snapshot with <username> and <password> into <course> of <ISBN> ISBN
       Then I navigate to unit view tab
       Then I Launch <AppName> App and Verify <Title> title
       And I extract events details from resource interaction
       Then I Refresh the page
       Examples:
         | username                 | password  | course                          | AppName       |  Title   |  ISBN          |
         | alok_inst@swlearning.com | Password1 |  MTPPR1GPNWJG                   |    Progress   |  Progress| 9781133354215 |


  @CAP-124
   Scenario Outline: Mindtap users Verify the App Launch event in Analytics Tool
    Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
    And I verify events generated for <EventAction> Events under <EventCategory> Event Category
    And I verify The Events Details like <EventSource> , <EventCategory> , <value>

    Examples:
      | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction | ISBN          | EventSource | value |
      | alok_inst@swlearning.com | course    | course-key     | MTPPR1GPNWJG | reverse | PROGRESS    | LAUNCH      | 9781133354215 | GTM         | 1     |