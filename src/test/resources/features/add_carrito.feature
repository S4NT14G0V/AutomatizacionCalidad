Feature: Agregar productos al carrito
Como usuario autenticado
Quiero agregar productos al carrito de compras
Para poder iniciar un proceso de compra

  Scenario: Usuario autenticado agrega productos al carrito
    Given que el usuario ha iniciado sesion
    And se encuentra en el catálogo de productos
    When agrega productos al carrito
    Then el contador del carrito debe aumentar según la cantidad agregada