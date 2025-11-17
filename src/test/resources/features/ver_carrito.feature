Feature: Visualizar carrito
Como usuario autenticado
Quiero revisar el carrito de compras
Para verificar los productos que he agregado

  Scenario: Usuario autenticado revisa el carrito
    Given que el usuario ha iniciado sesion
    And ha agregado productos al carrito
    When accede a la vista del carrito
    Then debe visualizar la lista de productos agregados