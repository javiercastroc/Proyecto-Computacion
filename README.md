# Tarea 1 CC3002-1 Metodologías de Diseño y Programación 2019, Primavera
Este programa no tiene metodo main, su implementación esta provada via test de JUnit}
Cada clase, interfaz y objetos tienen su´propio archivo de test.
Para la implementacion de equiparse items (armas) se utilizo double dispatch por extendibilidad
Para la implementacion del combate y diferencias de daño entre armas se utilizo double dispatch para asi hacer extendible el programa a proximas actualizaciones
Para la implementacion de ataques entre sorcereres armados con libros de magias se hizo un multiple dispatch que tambien llama al arma equipada de la unidad (unit) que esta siendo atacada (para hacer la distincion de luz vs oscuridad o luz vs anima por ejemplo).
En esta nueva version se solucionó el problema de combate de no diferenciar entre objetos que pueden atacar de los que no (u objetos que no deberían atacar lo hacían). Para esto se implementaron 2 interfaces nuevas (Iattackitem y Ihealitem) para diferenciar estos items con las especificaciones requeridas
Se agregó el paquete Game Controller, el cual actúa como un controlador del juego (toma decisiones sobre los objetos del modelo del juego)
Tambien se implementó la clase Tactician, la cual representa a un jugador y la responsabilidad de esta entidad es la de manejar todas las instrucciones del usuario y delegar mensajes a los objetos del modelo. Esto resulta en que el usuario no interactúe directamente con el modelo del juego.
Para la creación de units e items se crearon factories (dessign pattern)
Para algunas notificaciones y mensajes entre objetos (como las condiciones de victoria) se usaron handlers
