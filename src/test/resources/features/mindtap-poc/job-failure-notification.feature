Feature: Job Failure Notification (CAP-201)

@CAP-201
  Scenario Outline: Email Notification on job failure
  	Given I construct a request using base url <BASE_URL> and scheme <scheme> and following parameters : <UP1>,<JOB_NAME>,<UP2>,<UP3>
    When I hit a GET request 
    And I fetch job id
    Given I construct a request using base url <BASE_URL> and scheme <scheme> and following parameters : <UP1>,<JOB_NAME>,<JOB_ID>,<UP2>,<UP3>
    When I hit a GET request 
    And I fetch job status
    Then  I send Mail Notification For <JOB_NAME>

    Examples:
      | BASE_URL | JOB_NAME | JOB_ID | UP1     | UP2        | UP3    | scheme |
      | analytics-jenkins.cengage.info/view/QA%20Automation%20-%20Staging | RTA_MasterJob_Staging | {id} | job | api | json | http |


  @CAP-201PROD
  Scenario Outline: Email Notification on job failure
    Given I construct a request using base url <BASE_URL> and scheme <scheme> and following parameters : <UP1>,<JOB_NAME>,<UP2>,<UP3>
    When I hit a GET request
    And I fetch job id
    Given I construct a request using base url <BASE_URL> and scheme <scheme> and following parameters : <UP1>,<JOB_NAME>,<JOB_ID>,<UP2>,<UP3>
    When I hit a GET request
    And I fetch job status
    Then I send Mail Notification For <JOB_NAME>

    Examples:
      | BASE_URL | JOB_NAME | JOB_ID | UP1     | UP2        | UP3    | scheme |
      | analytics-jenkins.cengage.info/view/QA%20Automation%20-%20Prod | RTA_MasterJob_PROD | {id} | job | api | json | http |