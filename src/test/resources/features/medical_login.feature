# language: en
Feature: Login of Medical Admin
  As a medical admin user
  I want to log in
  To manage medical offices

  Scenario Outline: Login with different credentials
    Given está en la página de login
    When inicia sesión con usuario "<email>" y contraseña "<password>"
    Then el usuario debe ver el mensaje "<Mensaje>"

    Examples:
      | email                   | password  | Mensaje                      |
      | demo@medical.com | demo123 | Inicio de Sesión Exitoso     |
      | wrong@medoude.com       | wrong123! | Error    |
