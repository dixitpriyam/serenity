Feature: EngagementReport validations


   @CAP-12
   Scenario Outline: ADT user retrieves event counts
     Given As a user, I Get SSO Token for <User> from ADT
     Then I Validate legacy report with new report

     Examples:
       | User              |
       | 651d1b13e44c13f9:-eda1fb4:15a3dd9c2bc:-177|


#  @EngagementReport
#  Scenario Outline: ADT user retrieves event counts
#    Given As a user, I Get SSO Token for <User> from ADT
#    When I launch Engagement Report URL
#    Then I Should see the Engagement Report
#
#
#    Examples:
#      | User              |
#      | 651d1b13e44c13f9:-eda1fb4:15a3dd9c2bc:-177|
