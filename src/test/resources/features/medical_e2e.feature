# language: en
Feature: Medical Office E2E Management
  As an administrator
  I want to manage medical offices from start to finish
  To verify the complete workflow

  Scenario Outline: Manage an office from start to finish
    Given que el usuario ha iniciado sesión como administrador
    And que existe un consultorio con nombre "<nombreOriginal>"
    When se elimina el consultorio "<nombreOriginal>"
    And el usuario cierra sesión
    Then debe ver el login

    Examples:
      | nombreOriginal |
      | 21            |
      | 22            |