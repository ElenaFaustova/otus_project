Feature: Реализация BDD подхода

  @otus_open_main_page_test
  Scenario:
    Given user opens main page


  @otus_login_test
  Scenario Outline:
    Given user opens main page
    When user fills login "<login>"
    When user fills password "<password>"
    When user submits login

    Examples:
      | Название сценария | login            | password   |
      | Логин             | helenfaust@bk.ru | passOTtest |


  @otus_change_user_params_test
  Scenario Outline:
    Given user opens main page
    When user do login with email: "<login>" and password: "<password>"
    When user opens UserDetails page
    When user fills userFirstName "<userFirstName>"
    When user fills userLastName "<userLastName>"
    When user submits user parameter changes

    Examples:
      | Название сценария           | login            | password   | userFirstName | userLastName  |
      | Редактирование пользователя | helenfaust@bk.ru | passOTtest | Новое имя     | Новая фамилия |

    