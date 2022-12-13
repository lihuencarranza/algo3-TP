## Trabajo Práctio de la materia Algorítmos y Programación III - Cátedra Corsi/Essaya/Maraggi
## ETAPA 1
### Buscaminas

El juego elegido es el buscaminas. Sus reglas están en este [enlace](https://es.wikipedia.org/wiki/Buscaminas).

### Patrón de diseño

Se utilizará el patrón Flyweight. En el juego habrá casillas con diferentes valores: vacío, bomba, 1, 2, 3, 4, 5, 6, 7, 8, 9 (los números corresponden a la cantidad de bombas que limitan con el casillero. El patrón es útil porque permite mantener más objetos dentro de la cantidad disponible de RAM compartiendo las partes comunes del estado entre varios objetos en lugar de mantener toda la información en cada objeto. 

Supongamos que tenemos un a matriz de 2x2, una bomba y tres casilleros con valor 1. Como son cuatro casilleros, en vez de crear 4 objetos con diferentes atributos, se crea una clase para el "estado" del casillero. Esta clase almacena los estados posibles de los casilleros y en caso de crear uno nuevo busca en los estados ya existentes.

![image](https://www.plantuml.com/plantuml/png/RL2z3i8W4DvvYXiroWiq3eqc-W9kJGUWDn2XD41D6_NTFR0D7Lp8Tz_FVK0Tel1nj8Of8qA0cx08BmO7wIOCKC7PJVTvmAObJXcj7hiz-x35JI9vETeXpkILpXaKFJ5hsehezTIq5D-6ha95v-UKfId9X59Do8L7EgpCu98giIDS5YnYIak25KG_Of5B93cUEcXfi9Y8XnFdBZ_rDxqvNvSMOjDSRfoIy8VAkshiE_h9Bm00)

## ETAPA 2

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

## ETAPA 3

En la etapa anterior me falta implementar las banderitas. El problema que tenía es que si activaba una banderita en 
una caja, se activaba en todas las de su tipo (ya que por el patron, son todas las cajas del mismo valor las mismas 
pero en diferentes partes de la matriz). Entonces se me ocurrió crear otro vector con tipos de caja con banderita. 
Aun que esto en la práctica funciona bien, en el trabajo visualmente hay un bug con la cantidad de banderas que quedan.

Para los gráficos utilicé las imagenes de https://github.com/lxf44944/minesweeper_java

En caso de perder el juego pero queres seguir jugando, hay que tocar la carita triste que se genera en su 
correspondiente boton. Lo mismo sucede si se ganó el juego pero quiere jugar otra partida. 



