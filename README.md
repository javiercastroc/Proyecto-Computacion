# Tarea 1 CC3002-1 Metodologías de Diseño y Programación 2019, Primavera
Este programa no tiene metodo main, su implementación esta provada via test de JUnit}
Cada clase, interfaz y objetos tienen su´propio archivo de test.
Para la implementacion de equiparse items (armas) se utilizo double dispatch por extendibilidad
Para la implementacion del combate y diferencias de daño entre armas se utilizo double dispatch para asi hacer extendible el programa a proximas actualizaciones
Para la implementacion de ataques entre sorcereres armados con libros de magias se hizo un multiple dispatch que tambien llama al arma equipada de la unidad (unit) que esta siendo atacada (para hacer la distincion de luz vs oscuridad o luz vs anima por ejemplo).
