Feature: Bookmarks Create Validations
  @CAP-160
   Scenario Outline: Mindtap users Calculate the Total Bookmarks Create event in Analytics Tool
    Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
    Then I calculate total events generated for <EventAction> Events under <EventCategory> Event Category

    Examples:
      | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction | ISBN          | EventSource |
      | alok_inst@swlearning.com | course    | course-key     | MTPQN2PPN2LC | reverse | BOOKMARKS    | CREATE      | 9781133354215 | GTM         |

  @CAP-160
   Scenario Outline: Mindtap users performs a Bookmarks Create event
    Given I Launch the snapshot with <username> and <password> into <course> of <ISBN> ISBN
    Then I navigate to unit view tab
       And I select <Unit> unit
       And I select <Activity> reading activity
       And I Bookmark the page
       And I extract events details from resource interaction
       Examples:
          | username                 | password  | course            | Unit      |   ISBN    | Activity          |
          | alok_inst@swlearning.com | Password1 | MTPQN2PPN2LC    | CHAPTER 2:   | 9781133354215   | CHAPTER 2:        |

   @CAP-160
   Scenario Outline: Mindtap users Verify the Bookmarks Create event in Analytics Tool
     Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
     And I search and select a user with <Search_Data> in SSO UID
     When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
     And I verify events generated for <EventAction> Events under <EventCategory> Event Category
     And I verify The Events Details like <EventSource> , <EventCategory> , <value>

     Examples:
       | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction | ISBN          | EventSource | value |
       | alok_inst@swlearning.com | course    | course-key     | MTPQN2PPN2LC | reverse | BOOKMARKS    | CREATE      | 9781133354215 | GTM         | 1     |