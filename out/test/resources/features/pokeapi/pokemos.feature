Feature: Consumir recurso pokeapi
  Como usuario de pokeapi
  necesito obtener la lista de pokemones disponibles en el sitio
  para poder saber cuales se tienen registrados

  Scenario Outline: Pokemon registrado
    Given que el usuario necesita contultar que pokemones estan registrados, se hace la petición
    When se valide que el código de respuesta exitoso
    Then se validara que en los datos de retorno se encuentre el pokemon <pokemon>

    Examples:
      | pokemon   |
      | bulbasaur |
      | ivysaur   |

