//	Nombre: Amaia Casas Echevarria.
//	Fecha: 23/12/2020
//	Módulo: Programación.
//	UD4
//	PROG043 Tarea Evaluación 01.Realiza un programa en Java. 
//	Descripción: Con este programa trabajo la lectura y escritura de ficheros de texto y el manejo de
//               cadenas de caracteres, utilizando los 'Mad Libs'
//	Enlace a	video	autoevaluación:


import java.util.*;
import java.io.*;

public class madLibs {
   public static void main(String[] args) throws FileNotFoundException{
      // objeto tipo Scanner para introducir datos por teclado
     Scanner teclado = new Scanner(System.in);
          
     presentacion();
     // A continuación dependiendo de la opcion seleccionada que lo devuelve el método menu
     //   se llamará a un método u otro.
     String opcion = "";
     do {
        opcion = menu(teclado);
        
        if (opcion.equals("c")) {
         crearCuento();
         
        } else if (opcion.equals("v")) {
         verCuento();
         
        } else if(opcion.equals("s")) {
          System.out.println("Agur");
        }
     } while(!opcion.equals("s"));
       
   }// cierre main
   
/************************
** MÉTODO  presentacion *
************************/   
   // Método que muestra unas líneas de introducción y uso de este programa. 
   public static void presentacion() {
      System.out.println("Bienvenidos y bienvenidas al juego de los cuentos locos.");
      System.out.println("El programa te pedirá que introduzcas una serie de palabras");
      System.out.println("que se utilizarán para completar una historia.");
      System.out.println("El resultado se guardará en un fichero.");
      System.out.println("Puedes leer esas historias siempre que quieras.");  
      System.out.println(); 
   }
   
/************************
****** MÉTODO  menu *****
************************/ 
   //Este método muestra las opciones al usuario, tendrá que pulsar una de las letras indicadas, si no se repetirá
   //hasta que pulsa alguna letra de las indicadas.
   // Parámetros, objeto tipo Scanner
   //Return de la letra que ha pulsado
   public static String menu(Scanner teclado) {
     String opcion = "";
     do { 
        System.out.println("******* MENU *******");
        System.out.println("(C)rear un \"Mad Lib\"");
        System.out.println("(V)er un \"Mad Lib\"");
        System.out.println("(S)alir");
        System.out.print("Elija su opción: ");
        opcion = teclado.next().toLowerCase();
        System.out.println();
      }while(!opcion.equals("c") && !opcion.equals("v") && !opcion.equals("s"));
      return opcion;
   } 
   
/************************
** MÉTODO  crearCuento **
************************/  
   //Este método se ejecuta cuando se selecciona la opcion 'c',conecta con las diferentes
   // entradas y salidas para crear el cuento
   
   public static void crearCuento() {
      Scanner teclado = new Scanner(System.in);
      System.out.println("Crear un cuento:");
      
      try {
         Scanner leerFichero = obtenerConexion(teclado);
              
         //Solicitar el nombre del fichero de salida
         
         System.out.print("Nombre del fichero de salida: ");
         PrintStream escribirFichero = new PrintStream(new File(teclado.next()));
            
         while(leerFichero.hasNextLine()) {
            String linea = leerFichero.nextLine();
            procesarLinea(linea, escribirFichero, teclado);
         }
       
         System.out.println("El cuento loco ha sido creado\n");

         // Cierra la conexión
         leerFichero.close();
      } catch (Exception excepcion) {
         System.out.println("Excepción en crearCuento");
         }
   }
   
/************************
*** MÉTODO  verCuento ***
************************/ 
   //Método que muestra por pantalla el contenido del fichero que se selecciona
   //
   //
   public static void verCuento() {
      Scanner teclado = new Scanner(System.in);
      System.out.println("Ver un cuento:");
      try {
         Scanner leerFichero = obtenerConexion(teclado);
         procesar(leerFichero);
         leerFichero.close();
      } catch (Exception excepcion) {
         System.out.println("Excepción en verCuento");
      }
   }
   
/************************
*MÉTODO obtenerConexion *
************************/  
   // Este método comprueba que el fichero que introduce el usuario es correcto
   // Parámetros, objeto tipo Scanner
   // Return el objeto del fichero
   public static Scanner obtenerConexion(Scanner teclado) throws FileNotFoundException{
      System.out.print("Nombre del fichero que quieres leer: ");
      File fichero = new File(teclado.next());
       try {
         //Comprobar si el fichero se puede leer
         //Mientras no se pueda leer seguirá pidiendo un nombre de fichero válido.
         while(!fichero.canRead()) {
            System.out.println("Fichero no encontrado. Inténtalo otra vez");
            System.out.print("Nombre del fichero: ");
            fichero = new File(teclado.next());     
         }
                 
      } catch (Exception excepcion) {
         System.out.println("excepcion en obtenerConexion");
      }
      
   return new Scanner(fichero);
   }
   
/************************
**MÉTODO procesarLinea **
************************/  

   public static void procesarLinea(String texto, PrintStream escribir, Scanner teclado) {
      // La cadena de texto se convierte en Scanner para poder utilizar métodos
      // Parámetros, recibe el texto que contiene la linea y PrintStrean sobre el que se 
      // quiere escribir y el objeto Scanner teclado
      
      Scanner linea = new Scanner(texto);
      String total = "";
      while(linea.hasNext()) {
         String palabra = linea.next();
          // filtrar si la palabra empieza por < mostrar en pantalla
         if(palabra.startsWith("<") && palabra.endsWith(">")) {
            System.out.print(palabra.substring(1).replace(">",":").replace("-"," "));
            String respuesta = teclado.next();
            total += respuesta + " ";
         } else {
            total += palabra + " ";
         }
      }
      
      escribir.println(total);
   }
   
/************************
*****MÉTODO procesar ****
************************/ 

   //Método que procesa el fichero indicado para mostrarlo por pantalla
   // Parámetros tipo Scanner
   //  
   public static void procesar(Scanner entrada) {
      while (entrada.hasNextLine()) {
         String linea = entrada.nextLine();
         System.out.println(linea);
      }  
   } 
}// cierre public class