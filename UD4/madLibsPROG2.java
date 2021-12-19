//	Nombre: Amaia Casas Echevarria.
//	Fecha: 23/12/2020
//	M�dulo: Programaci�n.
//	UD4
//	PROG043 Tarea Evaluaci�n 01.Realiza un programa en Java. 
//	Descripci�n: Con este programa trabajo la lectura y escritura de ficheros de texto y el manejo de
//               cadenas de caracteres, utilizando los 'Mad Libs'
//	Enlace a	video	autoevaluaci�n:


import java.util.*;
import java.io.*;

public class madLibs {
   public static void main(String[] args) throws FileNotFoundException{
      // objeto tipo Scanner para introducir datos por teclado
     Scanner teclado = new Scanner(System.in);
          
     presentacion();
     // A continuaci�n dependiendo de la opcion seleccionada que lo devuelve el m�todo menu
     //   se llamar� a un m�todo u otro.
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
** M�TODO  presentacion *
************************/   
   // M�todo que muestra unas l�neas de introducci�n y uso de este programa. 
   public static void presentacion() {
      System.out.println("Bienvenidos y bienvenidas al juego de los cuentos locos.");
      System.out.println("El programa te pedir� que introduzcas una serie de palabras");
      System.out.println("que se utilizar�n para completar una historia.");
      System.out.println("El resultado se guardar� en un fichero.");
      System.out.println("Puedes leer esas historias siempre que quieras.");  
      System.out.println(); 
   }
   
/************************
****** M�TODO  menu *****
************************/ 
   //Este m�todo muestra las opciones al usuario, tendr� que pulsar una de las letras indicadas, si no se repetir�
   //hasta que pulsa alguna letra de las indicadas.
   // Par�metros, objeto tipo Scanner
   //Return de la letra que ha pulsado
   public static String menu(Scanner teclado) {
     String opcion = "";
     do { 
        System.out.println("******* MENU *******");
        System.out.println("(C)rear un \"Mad Lib\"");
        System.out.println("(V)er un \"Mad Lib\"");
        System.out.println("(S)alir");
        System.out.print("Elija su opci�n: ");
        opcion = teclado.next().toLowerCase();
        System.out.println();
      }while(!opcion.equals("c") && !opcion.equals("v") && !opcion.equals("s"));
      return opcion;
   } 
   
/************************
** M�TODO  crearCuento **
************************/  
   //Este m�todo se ejecuta cuando se selecciona la opcion 'c',conecta con las diferentes
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

         // Cierra la conexi�n
         leerFichero.close();
      } catch (Exception excepcion) {
         System.out.println("Excepci�n en crearCuento");
         }
   }
   
/************************
*** M�TODO  verCuento ***
************************/ 
   //M�todo que muestra por pantalla el contenido del fichero que se selecciona
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
         System.out.println("Excepci�n en verCuento");
      }
   }
   
/************************
*M�TODO obtenerConexion *
************************/  
   // Este m�todo comprueba que el fichero que introduce el usuario es correcto
   // Par�metros, objeto tipo Scanner
   // Return el objeto del fichero
   public static Scanner obtenerConexion(Scanner teclado) throws FileNotFoundException{
      System.out.print("Nombre del fichero que quieres leer: ");
      File fichero = new File(teclado.next());
       try {
         //Comprobar si el fichero se puede leer
         //Mientras no se pueda leer seguir� pidiendo un nombre de fichero v�lido.
         while(!fichero.canRead()) {
            System.out.println("Fichero no encontrado. Int�ntalo otra vez");
            System.out.print("Nombre del fichero: ");
            fichero = new File(teclado.next());     
         }
                 
      } catch (Exception excepcion) {
         System.out.println("excepcion en obtenerConexion");
      }
      
   return new Scanner(fichero);
   }
   
/************************
**M�TODO procesarLinea **
************************/  

   public static void procesarLinea(String texto, PrintStream escribir, Scanner teclado) {
      // La cadena de texto se convierte en Scanner para poder utilizar m�todos
      // Par�metros, recibe el texto que contiene la linea y PrintStrean sobre el que se 
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
*****M�TODO procesar ****
************************/ 

   //M�todo que procesa el fichero indicado para mostrarlo por pantalla
   // Par�metros tipo Scanner
   //  
   public static void procesar(Scanner entrada) {
      while (entrada.hasNextLine()) {
         String linea = entrada.nextLine();
         System.out.println(linea);
      }  
   } 
}// cierre public class