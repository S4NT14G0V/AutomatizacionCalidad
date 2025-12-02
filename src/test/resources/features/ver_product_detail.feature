Feature: Visualizar información de un productos
Como usuario autenticado
Quiero ver la información detallada de un productos
para conocer toda la información del producto

  Scenario: Usuario autenticado visualiza información de un producto
    Given que el usuario ha iniciado sesion
    And se encuentra en el catálogo de productos
    When selecciona un producto
    Then visualiza toda la información detallada del producto