Feature: Reading Acitvity  (CAP-132)


  @CAP-132
  Scenario Outline: Mindtap users Calculate the Total Reading Acitvity event in Analytics Tool
    Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
    Then I calculate total events generated for <EventAction> Events under <EventCategory> Event Category

    Examples:
      | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction     | ISBN          | EventSource |
      | alok_inst@swlearning.com | course    | course-key     | MTPPR1GPNWJG | reverse | READING       | LAUNCH          | 9781133354215 | GTM         |
      | alok_inst@swlearning.com | course    | course-key     | MTPPR1GPNWJG | reverse | READING       | VIEW            | 9781133354215 | GTM         |

  @CAP-132
  Scenario Outline: Mintap user performs reading launch and view event
    Given I Launch the snapshot with <username> and <password> into <course> of <ISBN> ISBN
    When I select Full Book app from app dock
    Then I should see the book content
    And I extract events details from resource interaction
    Examples:
      | username                 | password  | course          | Environment   |ISBN|
      | alok_inst@swlearning.com | Password1 | MTPPR1GPNWJG    | int           | 9781133354215 |

#  @CAP-132
#  Scenario Outline: Mintap user performs reading activity and view event
#    Given I Launch the snapshot with <username> and <password> into <course> of <ISBN> ISBN
#    Given I am on course page
#    When I select a <chapter> from <view>
#    Then I should be able to view and read the content
#    And I extract events details from resource interaction
#    Examples:
#      | username                 | password  | course          | Environment   |ISBN           |chapter    |view|
#      | alok_inst@swlearning.com | Password1 | MTPP4F4PPXJK    | int           | 9781133354215 |CHAPTER 3: | Unit View|

  @CAP-132
  Scenario Outline: ADT user retrieves event counts
    Given As a user, I launch ADT URL with the following query string <QueryType> , <QueryAttribute> , <Parameter> , <Tab> , <ISBN>
    And I search and select a user with <Search_Data> in SSO UID
    When I search events with the following parameter <EventCategory>,<EventAction>,<EventSource>
    And I verify events generated for <EventAction> Events under <EventCategory> Event Category
    And I verify The Events Details like <EventSource> , <EventCategory> , <value>


    Examples:
      | Search_Data              | QueryType | QueryAttribute | Parameter    | Tab     | EventCategory | EventAction     | ISBN          | EventSource | value |
      | alok_inst@swlearning.com | course    | course-key     | MTPPR1GPNWJG | reverse | READING       | LAUNCH          | 9781133354215 | GTM         | 1     |
      | alok_inst@swlearning.com | course    | course-key     | MTPPR1GPNWJG | reverse | READING       | VIEW            | 9781133354215 | GTM         | 1     |



