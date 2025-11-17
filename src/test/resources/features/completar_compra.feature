Feature: Finalizar compra
Como usuario autenticado
Quiero finalizar mi compra
Para completar el proceso

  Scenario: Usuario completa informacion de compra
    Given que el usuario ha iniciado sesion
    When accede a la página de checkout
    And ingresa su informacion personal
    Then se debe continuar al resumen del pedido

  Scenario: Usuario no completa informacion de compra
    Given que el usuario ha iniciado sesion
    When accede a la página de checkout
    And deja campos obligatorios vacíos
    Then se debe mostrar un mensaje de error

  Scenario: Usuario completa el proceso de compra
    Given que el usuario ha iniciado sesion
    And ha avanzado del proceso de checkout
    When confirma la compra
    Then se debe mostrar un mensaje de confirmacion