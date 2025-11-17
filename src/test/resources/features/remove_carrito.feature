Feature: Eliminar productos del carrito
Como usuario autenticado
Quiero eliminar productos del carrito
Para corregir mis compras antes de pagar

  Scenario: Usuario elimina productos del carrito
    Given que el usuario ha iniciado sesion
    And tiene productos dentro del carrito
    When elimina productos
    Then se debe actualizar el contador del carrito
    And los productos eliminados no deben aparecer en la lista del carrito