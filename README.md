## Trabajo Práctio de la materia Algorítmos y Programación III - Cátedra Corsi/Essaya/Maraggi

### Buscaminas

El juego elegido es el buscaminas. Sus reglas están en este [enlace](https://es.wikipedia.org/wiki/Buscaminas).

### Patrón de diseño

Se utilizará el patrón Flyweight. En el juego habrá casillas con diferentes valores: vacío, bomba, 1, 2, 3, 4, 5, 6, 7, 8, 9 (los números corresponden a la cantidad de bombas que limitan con el casillero. El patrón es útil porque permite mantener más objetos dentro de la cantidad disponible de RAM compartiendo las partes comunes del estado entre varios objetos en lugar de mantener toda la información en cada objeto. 

Supongamos que tenemos un a matriz de 2x2, una bomba y tres casilleros con valor 1. Como son cuatro casilleros, en vez de crear 4 objetos con diferentes atributos, se crea una clase para el "estado" del casillero. Esta clase almacena los estados posibles de los casilleros y en caso de crear uno nuevo busca en los estados ya existentes.

![image](https://user-images.githubusercontent.com/86395729/201571569-21a5331c-82d5-4a13-97bb-0e788c92c01a.png)



#### Nombres de las clases

Dentro de los nombres de las clases utilizaré varias veces MN como referencia a MinesWeeper (Buscaminas en inglés) ya que es un nombre muy largo y la idea de es que de cada clase nos quedemos con su utilidad y no la repetición del nombre.
La I delante de una clase refiere a que la clase es una interface.
La r delante como prefijo de una variable refiere a random (aleatorio).

## Paso a Paso
En primer lugar, para organizarme y tener una idea clara de lo que estoy haciendo, decidí comenzar con la creación de la Matriz. Lo que hice fue crear una matriz (Board) de objetos tipo Box (es decir, la cajita clickeable). Primero creé las bombas, o mejor dicho la bomba porque en realidad son todas la misma bomba ya que utilizo el patrón de diseño Flyweight y de esta manera ahorro memoria ya que todas tienen los mismos atributos, métodos y se comportan exactamente igual. En un principio iba a hacer tres categorias de Box: Bomb, Number, Empty. Pero me di cuenta que todas tendrían un número ya que las Empty tendrían un 0 y las Number su numero. 

-Bomb es una subclase de Box.
-Box tiene la capacidad de mostrar la cantidad de bombas que tiene alrededor sin ser una bomba. En el caso de que su numero sea 10, significa que es una bomba y se comportará y verá como una.

Luego de crear una Bomba, creo su entorno. A cada box que esté conectado con la bomba se le sumará 1. De esta manera si ya están cerca de otra bomba, aumentará la cantidad de bombas cercanas (como es en las instrucciones del juego). 
Una vez creadas las bombas y las casillas con numeros, queda agregar las casillas que no tienen nada cerca. Para esto agregué una clase llamada ClickedBox para que hereda se Box. Esta casilla no es más clickeable y tampoco puede ponersele una banderita.

## Falta implementar

-Implementar bien la banderita
-Una mejor interfaz (hay que limpiar la pantalla porque queda todo feo).
-Creo que los nombres de las columnas y filas los tengo a veces bien y otras mal. Tengo que modificar bien esto.


