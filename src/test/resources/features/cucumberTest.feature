Feature: Реализация BDD подхода

  @otus_open_main_page_test
  Scenario: Open main page
    Given user opens main page


  @otus_login_test
  Scenario Outline: Login
    Given user opens main page
    Given user opens RegisterForm
    When user fills login "<login>"
    When user fills password "<password>"
    When user submits login

    Examples:
      | Название сценария | login            | password   |
      | Логин             | helenfaust@bk.ru | passOTtest |


  @otus_open_contacts
  Scenario Outline: Open Contacts
    Given user opens main page
    When user do login with email: "<login>" and password: "<password>"
    When user opens contacts
    Then user checks that text 'Реквизиты' exists

    Examples:
      | Название сценария           | login            | password   |
      | Редактирование пользователя | helenfaust@bk.ru | passOTtest |

    