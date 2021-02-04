Feature: Реализация BDD подхода

  @otus_open_main_page_test
  Scenario:
    * user opens main page


  @otus_login_test
  Scenario Outline:
    * user opens main page
    * user fills login "<login>"
    * user fills password "<password>"
    * user submits login

    Examples:
      | Название сценария | login            | password   |
      | Логин             | helenfaust@bk.ru | passOTtest |


  @otus_change_user_params_test
  Scenario Outline:
    * user opens main page
    * user do login with email: "<login>" and password: "<password>"
    * user opens UserDetails page
    * user fills userFirstName "<userFirstName>"
    * user fills userLastName "<userLastName>"
    * user submits user parameter changes

    Examples:
      | Название сценария           | login            | password   | userFirstName | userLastName  |
      | Редактирование пользователя | helenfaust@bk.ru | passOTtest | Новое имя     | Новая фамилия |

    