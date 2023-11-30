# Documentacion Proyecto2 Turnero

El proyecto crea una web que permite al usuario usarla de turnero, de forma que 
se puede añadir ciudadanos y turnos, asi como mostrar los mismos con algunos filtros.

La estructura del proyecto se completa con una conexion JPA para conectar con la base de datos.
Se ha separado la estructura en favor del orden, de tal manera que quede de la siguiente forma:

-**index.jspa**
	Contiene el HTML de la web asi como el CSS basico para crear la visualizacion de la misma
	Cosas a mencionar:
	Se separa la web en dos partes, una para ciudadano y otra para turno, utilizando la etiqueta de css de flex
	Se manejan errores mediante codigo java que obtiene el parametro de vuelta comprobando el error
	En la parte de turno se manejan los distintos botones con un switch que devuelva la lista solicitada

-**Carpeta Logica**
	Contiene los objetos basicos de ciudadano y turno ademas de la Controladora que maneja ambos objetos

-**Carpeta Persistencia**
	Aqui se encuentran los ControllersJPA y la controladora de persistencia
	Los controllers de los objetos son autogenerados, excepto un añadido para que se
	  pueda buscar por nombre.
	
-**Carpeta Servlets**
	Los servlets cuentan con el Post y el Get, el primero para recibir la informacion de los forms y crear el 	objeto en la base de datos.
	En el Get de Turnos hay mas contenido ya que maneja tres botones, mostrar lso turnos, filtrar por fecha,
	  y filtrar de nuevo por estado, ademas de los filtros utilizar las funciones de stream y collections

## Comprobaciones y Pruebas
Los botones de Añadir comprueban si hay algun campo vacio, no solo para asegurar que no se ha dejado asi por error, si no tambien 
para evitar un error a la hora de mostrar, que al pasar un campo vacio falla en las tablas comunicadas, por ejemplo en la tabla turnos, 
que si introducias un turno sin un ciudadano, dejaba de mostrar a partir de este.
Los id se añaden automaticamente para no tener errores de repeticion
Tambien se controla que el id al que se asocia el ciudadano con el turno exista antes de añadirlo

