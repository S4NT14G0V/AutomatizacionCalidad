Feature: Inicio de sesión
  Como: usuario registrado
  Quiero iniciar sesion en la tienda
  Para poder acceder a mi cuenta

  Scenario: Usuario registrado inicia sesión exitosamente
    Given que el usuario está en la página de login
    When ingresa su usuario y contraseña correctos
    Then el sistema lo redirige al catálogo de productos

  Scenario: Usuario inicia sesión con credenciales incorrectas
    Given que el usuario está en la página de login
    When ingresa un usuario o contraseña incorrectos
    Then el sistema muestra un mensaje de error

  Scenario: Usuario inicia sesion con cuenta bloqueada
    Given que el usuario está en la página de login
    When ingresa las credenciales de una cuenta bloqueada
    Then el sistema muestra un mensaje de error