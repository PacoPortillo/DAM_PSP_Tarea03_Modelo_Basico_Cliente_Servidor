Modelo básico Cliente Servidor
==============================

_ _ _
Esta tarea ha sido publicada como solución en la plataforma de Educación de la Junta de Andalucía del C.F.G.S. Desarrollo de Aplicaciones Multiplataforma a Distancia del módulo de Programación de Servicios y Procesos.

_ _ _
## Características:
Este trabajo consta de dos aplicaciones que implementan un modelo básico de Cliente / Servidor.

- - -

Implementar un Servidor de Tiempo con las siguientes características:
- Puerto de escucha 50000.
- Aceptará únicamente una conexión de un cliente.
- Podrá recibir los siguientes comandos procedentes del cliente:
-- DAY. El servidor enviará al cliente el día de la fecha actual.
-- MONTH. Enviará al cliente el mes de la fecha actual.
-- YEAR. Transferirá el año de la fecha actual.
-- TIME. Enviará la hora en formato HH:mm:ss.
-- ALL. Enviará al cliente tanto la fecha como la hora en el siguiente formato HH:mm:ss dd/MM/yyyy.
-- END. Se finalizará la conexión con el cliente y se termianrá la ejecución del servidor.

Para ejecutarlo doble clic sobre /Servidor_jar/Servidor.jar

- - -

Programar un Cliente de Tiempo con las siguientes características:
- Se conectará al servidor local en el puerto 50000 del mismo.
- Le pedirá al usuario que introduzca por teclado el comando a consultar al servidor.
- El cliente enviará el comando solicitado e imprimirá por consola la respuesta del mismo.
- Este proceso se repetirá hasta que el usuario introduzca el comando END.
- Por último, se cerrarán todas las conexiones con el servidor.

Para ejecutarlo doble clic sobre /Cliente_jar/Cliente.jar

- - -
## Imágenes:
![img01]

- - -
## Requisitos
- [Java] 7 o superior (para ejecutar la Aplicación).

- - -
## Instalación:
- No precisa, se ejecuta el /Servidor\_jar/Servidor.jar y el /Cliente\_jar/Cliente.jar.
Nota: Las carpetas images deben estar en la misma ubicación que los archivos .jar para su correcta ejecución.

- - -
## Entorno de desarrollo
- La aplicación ha sido desarrollada utilizando el IDE [NetBeans].

- - -
## Licencia
Esta aplicación se ofrece bajo licencia [Creative Commons Attribution 4.0](https://choosealicense.com/licenses/cc-by-4.0/).

- - -
## Fecha:
15 de enero de 2018

- - -


[img01]: ./readme_imagenes/img01.jpg
[Java]: https://www.java.com/
[NetBeans]: https://netbeans.org/



