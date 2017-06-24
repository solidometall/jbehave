Meta:
@filtro TOUCH

Narrative:
As a cliente de un CAC
I want to generar un turno desde el touch
So that puede ser atendido por un representante

Scenario: nuevo turno no cliente [AQPLUS-1481]
Given el touch del CAC JBT
Then verifico pantalla principal
When selecciono opcion no cliente en el touch
And selecciono proceso en el touch
Then se genera un turno de atencion en el touch

Scenario: nuevo turno no cliente con servicios [AQPLUS-1481]
Given el touch del CAC JBT2
When selecciono servicio en el touch
Then verifico pantalla principal
When selecciono opcion no cliente en el touch
And selecciono proceso en el touch
Then se genera un turno de atencion en el touch

Scenario: nuevo turno cliente no autenticado con servicios
Given el touch del CAC JBT2
When selecciono servicio en el touch
Then verifico pantalla principal
When ingreso numero de telefono Cliente no autenticado
And presiono el boton continuar
And selecciono proceso en el touch
Then se genera un turno de atencion en el touch

