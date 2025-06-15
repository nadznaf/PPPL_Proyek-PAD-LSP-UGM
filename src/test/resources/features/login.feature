Feature: Developer Login

  @positive
  Scenario Outline: Valid login with ugm account 1
    Given the user is on the developer login page
    When the user enters email "<email>" and password "<password>"
    And the user clicks the login button
    Then the system doing authentification and user redirected to the dashboard

    Examples:
      | email                    | password   |
      | adminDwi@ugm.ac.id       | AdminDwi   |

  @positive
  Scenario Outline: Valid login with ugm account 2
    Given the user is on the developer login page
    When the user enters email "<email>" and password "<password>"
    And the user clicks the login button
    Then the system doing authentification and user redirected to the dashboard

    Examples:
      | email                    | password   |
      | adminNafa@mail.ugm.ac.id | AdminNafa  |

  @negative
  Scenario Outline: Invalid login with wrong credentials
    Given the user is on the developer login page
    When the user enters email "<email>" and password "<password>"
    And the user clicks the login button
    Then the login process should be failed and user stay on login page displaying an alert

    Examples:
      | email                    | password   |
      | adminDwi@gmail.com       | AdminDwi   |
