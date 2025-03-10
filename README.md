# CLIENTE
Es preferible probar la clase Cliente, en ella se podrá ver todo de mejor manera, además que se mostrará por pantalla lo enviado por el servidor.

# PROTOCOLO
El protocolo será el siguiente:
- El cliente deberá enviar al servidor la información de la siguiente manera (recalcar que los ">" no los escribe el cliente):
```
> 1
> *
> 4
```
Una vez el cliente haya seguido este protocolo(primero número, luego operador y por último número), pudiendo sustituir 1 y 4 por cualquier otro número entero sin superar el límite que marca INT como unidad de medida, el servidor responderá al cliente de la siguiente forma:
```
SERVER:
 RESULTADO -> 4
 GASTOS: 10 CÉNTIMOS
```
Recalcar que las operaciones sencillas sumarán si se realiza una operacion con "+" o "-" y las complejas si se reliza con "*" o "/".

# EXCEPCIONES
La mayoría de excepciones se tratan todas en el servidor, a traves de extensiones en los métodos (throws Exception). Pero también se tratarán a través de try_catch y try_resources, además, dependiendo de la información que el usuario le pase al servidor se manejarán ciertos tipos de excepciones y errores.

# LOG
El fichero log constará de las siguientes características:
- Nombre: el nombre del puerto del cliente.
- Contenido: un apartado de información con la actividad de cada cliente (tipo INFO).