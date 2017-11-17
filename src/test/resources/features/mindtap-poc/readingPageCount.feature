Feature: Reading Page Count event validations

   @CAP-198
     Scenario Outline: Mindtap users performs a App Launch event
         Given I Launch the snapshot with <username> and <password> into <course> of <ISBN> ISBN
         Then I navigate to unit view tab
         Then I Launch <AppName> App and Verify <Title> title
         And I navigate to <pageNumber> page
         And I extract events details from resource interaction
         And I verify readingPageCount attribute present in Networklogs
         Then I Refresh the page
         Examples:
           | username                 | password  | course                          | AppName       |  Title   | Environment  | ISBN          |pageNumber|
           | alok_inst@swlearning.com | Password1 |  MTPPR1GPNWJG                   |    Full Book   |  Full Book| int          | 9781133354215 |        1|
