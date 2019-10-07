Errores encontrados en código inicial:

No se especifica el esquema en la conección.
El script para insertar genera error por no tener la palabra clave 'VALUES'

En la línea 75 se ejecuta un méotod trim al parámetro de entrada String, antes de validar si este es no nulo, 
lo que causará un nullPointer

la variable "l" nunca es utilizado.

en la línea 146 "l" esta siendo sumado con sigo mismo, el cual ha empezado como nulo. al sumar un nulo con texto, "null" 
se interpreta como texto.

Pese a hacer atnto énfasis en el tipo de mensaje qeu se envía, en los snipet que registran el log se les pone a 
todos "Level.INFO"

debido a que wse utiliza el mismo ojeto logger el mensaje se ejecuta 2 veces






Quiero:

inicializar el objeto una sola vez.




