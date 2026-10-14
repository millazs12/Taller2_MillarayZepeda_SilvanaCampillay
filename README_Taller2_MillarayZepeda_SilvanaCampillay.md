Sistema de registro de consumo POO

-Descripcion del proyecto-

El proyecto consiste en el desarrollo de un programa que permite gestionar y realizar un seguimiento del consumo de dos tipos de productos (choripan y terremoto) por parte de diferentes participantes. El sistema facilita la visualización, incorporación, edición y eliminación de registros, manteniendo la información organizada y actualizada.

Además, permite calcular métricas relacionadas con las cantidades consumidas, tanto a nivel individual como general, e incorpora un contador de los días en que cada participante alcanza un umbral de consumo previamente establecido.

-Integrantes-

1. Millaray Zepeda (ICCI) - 22.063.994-0 - millazs12

2. Silvana Campillay (ICI) - 21.862.853-2 - Npieer

-Estructura del proyecto-

Los paquetes utilizados fueron:

 - java.io.File
 - java.io.FileWriter
 - java.io.BufferedWriter
 - java.io.IOException
 - java.util.Scanner
 
Las clases principales son:
 - Main
 - GestorFonda
 - Choripan
 - Terremoto
 - Participante

-Instrucciones de ejecucion- 

Requisitos:
Tener instalado Java (JDK 8 o superior) y un entorno de desarrollo como Eclipse.

Clonar e ingresar al proyecto:
Para descargar el código y entrar a la carpeta desde la terminal, hay que ejecutar esto:

git clone https://github.com/millazs12/Taller2_MillarayZepeda_SilvanaCampillay.git

cd Taller2_MillarayZepeda_SilvanaCampillay

Los archivos de texto (Participantes.txt y Consumidos.txt) ya vienen incluidos en la raíz del proyecto para que el programa pueda leerlos sin problemas.

Cómo ejecutar el programa:

 - Opción 1: Usando un IDE (Eclipse)
  1. Importar el proyecto como Java Project existente.
  2. Ubicar la clase Main.java dentro del paquete taller2.
  3. Hacer clic derecho y seleccionar Run As > Java Application.
 

- Opción 2: Usando la terminal
  
Estando dentro de la carpeta del proyecto

1. Compilar:
   
javac src/taller2/Main.java

2. Y luego ejecutar:
   
java -cp src taller2.Main
