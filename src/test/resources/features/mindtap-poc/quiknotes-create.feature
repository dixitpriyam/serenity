Feature: QUICKNOTE CREATE validations (CAP-151)


   @CAP-151
   Scenario Outline: ADT user retrieves event counts
     Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
     And I search and select a user with <Search_Data> in SSO UID
     When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
     Then I calculate total events generated for <EventAction> Events under <EventCategory> Event Category

     Examples:
       | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction     | ISBN          | EventSource |
       | alok_inst@swlearning.com | course    | course-key     | MTPP4F4PPXJK | reverse | QUICKNOTE       | CREATE   | 9781133354215 | GTM         |

   @CAP-151
   Scenario Outline: Mindtap users CREATE QUICKNOTE in Reader
      Given I Launch the snapshot with <username> and <password> into <course> of <ISBN> ISBN
      Then I navigate to unit view tab
      And I select <Unit> unit
      And I select <Activity> reading activity
#     And I navigate to <Section> section
      And I create a QUICKNOTE
      And I Delete a QUICKNOTE
      And I extract events details from resource interaction

      Examples:
         | username                 | password  | course            | Unit             | Activity          | Section            | Environment  | ISBN |
         | alok_inst@swlearning.com | Password1 | MTPP4F4PPXJK    | CHAPTER 2:       | CHAPTER 2:        | Major Body Cavities           |int | 9781133354215 |       



   @CAP-151
   Scenario Outline: Mindtap users Verify the QUICKNOTE CREATE event in Analytics Tool
     Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
     And I search and select a user with <Search_Data> in SSO UID
     When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
     And I verify events generated for <EventAction> Events under <EventCategory> Event Category
     And I verify The Events Details like <EventSource> , <EventCategory> , <value>


     Examples:
       | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction     | ISBN          | EventSource | value |
       | alok_inst@swlearning.com | course    | course-key     | MTPP4F4PPXJK | reverse | QUICKNOTE       |     CREATE     | 9781133354215 | GTM         | 1     |




