Feature: Cerrar sesión
Como usuario autenticado
Quiero cerrar mi sesión
Para proteger mi cuenta

  Scenario: Usuario autenticado cierra su sesion
    Given que el usuario ha iniciado sesion
    And abre el menu
    When cierra sesion
    Then debe ser redirigirlo al login