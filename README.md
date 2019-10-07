## EJERCICIO BELATRIX

**Errores encontrados en código inicial:**

- No se especifica el esquema en la conección.

- El script para insertar genera error por no tener la palabra clave 'VALUES'

- Se ejecuta un método trim al parámetro de entrada String antes de validar si este es no nulo 
lo que causará un nullPointer

- La variable "l" nunca es utilizado.

- La variable "l" esta siendo sumado con sigo misma, la cual ha empezado como nulo. al sumar un nulo con texto, "null" 
se interpreta como texto.

- Pese a hacer énfasis en el tipo de mensaje que se envía, todo se registra el log con "Level.INFO"

- La conexión a base de datos está consumiendo demasiados recursos al no ser cerrada nunca

- Se está violando demasiado el principio de Responsabilidad Única.



**Cambios Realizados:**

- Se ha creado la clase DatabaseHandler, aprovechando la clase abstracta Handler para realizar las conecciones y 
registro en base de datos siguiendo el paradigma de los otros Handler.

- Se ha modificado el constructor de JobHandler para recibir menos parámetros de entrada, donde solo se especifica que 
que tipos de salida va a utilizar. Además, ahora es el encargado de la configuración del Logger para solo realizar esta tarea una vez.

- Se ha separado el método modificado el metodo LogMessage para que solo realice registros de INFORMACIÓN. A su vez, se han creado los metodos
logWarning y logError, los cuales se registran ALERTAS y ERRORES respectivamente. Esto con la finalidad de tener una mejor lectura y menos parámetros. 
Es mejor tener 3 metodos de 1 parámetro que 1 método con 3 parámetros.

- Se ha refactorizado el código tratando de seguir criterios de Codigo Limpio y Responsabilidad Única.

- Se han agregado validaciones.








