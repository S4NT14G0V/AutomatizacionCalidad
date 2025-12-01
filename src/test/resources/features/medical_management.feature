# language: en
Feature: Medical Management
  As an administrator
  I want to manage medical offices
  To keep updated information about the offices

  @medical_management @add_clinic
  Scenario Outline: Add a new office
    Given que el usuario ha iniciado sesión como administrador
    When crea un nuevo consultorio con nombre "<nombre>", especialidad "<especialidad>" y ubicación "<sede>"
    Then debe ver el mensaje "<Mensaje>"

    Examples:
      | nombre | especialidad        | sede           | Mensaje                                          |
      | 23    | Odontología         | Sede Norte     | Consultorio Registrado                           |
      | 24    | Pediatría           | Sede Central   | Consultorio Registrado                           |
      | 25     | Medicina General    | Sede Sur       | El número de consultorio ya existe en la Sede    |

  @medical_management @edit_clinic
  Scenario Outline: Edit an existing office
    Given que el usuario ha iniciado sesión como administrador
    And que existe un consultorio con nombre "<nombreOriginal>"
    When se edita el consultorio "<nombreOriginal>" cambiando el nombre a "<nuevoNombre>" y la especialidad a "<nuevaEspecialidad>"
    Then debe ver el mensaje "<Mensaje>"

    Examples:
      | nombreOriginal | nuevoNombre | nuevaEspecialidad | Mensaje                  |
      | 26            | 776         | Odontología       | Consultorio actualizado  |
      | 27            | 777         | Cardiología       | Consultorio actualizado  |

  @medical_management @delete_clinic
  Scenario Outline: Delete an existing office
    Given que el usuario ha iniciado sesión como administrador
    And que existe un consultorio con nombre "<nombreOriginal>"
    When se elimina el consultorio "<nombreOriginal>"
    Then debe ver el mensaje "<Mensaje>"

    Examples:
      | nombreOriginal | Mensaje                 |
      | 28            | Consultorio eliminado   |
      | 29            | Consultorio eliminado   |