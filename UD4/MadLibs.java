//	Nombre: Amaia Casas Echevarria.
//	Fecha: 23/12/2021
//	Módulo: Programación.
//	UD4
//	PROG043 Tarea Evaluación 01.Realiza un programa en Java. 
//	Descripción: Con este programa trabajo la lectura y escritura de ficheros de texto y el manejo de
//               cadenas de caracteres, utilizando los 'Mad Libs'
//	Enlace a	video	autoevaluación:

import java.util.*;
import java.io.*;

public class MadLibs {
    // objeto tipo Scanner para introducir datos por teclado
    static Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException {
        presentacion();

        char opcion='0';
        do {   
            // menu devuelve un valor tipo char         
            opcion = menu(teclado);
            switch (opcion) {
                case 'c':                   
                    crearCuento(teclado);
                    break;
                case 'v':
                    verCuento(teclado);
                    break;
                case 's':
                    System.out.println("AGUR");        
            }
        } while (opcion != 's');
        
    }// fin main

/************************
** MÉTODO  presentacion *
************************/ 
// Método que muestra unas líneas de introducción y uso de este programa. 
    public static void presentacion() {
        System.out.println("Bienvenidos y bienvenidas al juego de los cuentos locos.\n"
                            + "El programa te pedirá que introduzcas una serie de palabras\n"
                            + "que se utilizarán para completar una historia.\n" 
                            + "El resultado se guardará en un fichero.\n"
                            + "Puedes leer esas historias siempre que quieras.\n");
    }// fin presentacion

/************************
****** MÉTODO  menu *****
************************/ 
   /*Este método muestra las opciones al usuario, tendrá que pulsar una de las letras indicadas o palabra, si no se repetirá
     Parámetros: objeto tipo Scanner
     Return de la letra que ha pulsado*/
     
    public static char menu(Scanner teclado) {
        //String opcion = "";
        char opcion = '0';
     do { 
        System.out.println("******* MENU *******");
        System.out.println("(C)rear un \"Mad Lib\"");
        System.out.println("(V)er un \"Mad Lib\"");
        System.out.println("(S)alir");
        System.out.print("Elija su opción: ");
        System.out.println();
        // Recoge solo la primera letra del texto introducido
                    //char letra = teclado.next().charAt(0);
        opcion = teclado.next().charAt(0);
        
                    // Convierte char a String para poder usar los métodos de String
        //opcion = String.valueOf(letra);
        // Lo pasa a minúsculas
        opcion = Character.toLowerCase(opcion);
      }while(opcion != 'c' && opcion != 'v' && opcion != 's');//!opcion.equals("c") && !opcion.equals("v") && !opcion.equals("s"));
      return opcion;
    }

/************************
** MÉTODO  crearCuento***
************************/  
   /*Este método se ejecuta cuando se selecciona la opcion 'c',conecta con las diferentes
     entradas y salidas para crear el cuento
     Parámetros: objeto Scanner*/
   public static void crearCuento(Scanner teclado) throws FileNotFoundException {
      System.out.println("Crear un cuento:"); 
      Scanner leer = conexion(teclado);
      

     //Solicitar el nombre del fichero de salida         
     System.out.print("Nombre del fichero de salida: ");
     PrintStream escribirFichero = new PrintStream(new File(teclado.next()));

     while(leer.hasNextLine()) {
        String linea = leer.nextLine();
        procesarLinea(linea, escribirFichero, teclado);
     }
     System.out.println("El cuento loco ha sido creado\n");

     // Cierra la conexión
     leer.close();
   }

/************************C
*** MÉTODO verCuento ****
************************/ 
    /*Método que muestra por pantalla el contenido del fichero que se selecciona
      Parámetros: objeto Scanner*/
    public static void verCuento(Scanner teclado) throws FileNotFoundException {
        System.out.println("Ver un cuento:");
        Scanner leer = conexion(teclado);
        procesar(leer);
}

/*****************
*MÉTODO conexion**
******************/ 
    /*Este método comprueba que el fichero que introduce el usuario es correcto
      Parámetro: objeto tipo Scanner
      Return: el objeto del fichero*/
    public static Scanner conexion(Scanner teclado) throws FileNotFoundException {
      System.out.println("Nombre del fichero que quieres leer: ");
      File fichero = new File(teclado.next());
      // Comprobar si el fichero se puede leer, sino seguirá pidiendo un nombre de fichero válido
      while(!fichero.canRead()) {
        System.out.println("Fichero no encontrado. Inténtalo otra vez");
        System.out.print("Nombre del fichero: ");
        fichero = new File(teclado.next());     
     }
     return new Scanner(fichero);
    }

/************************
**MÉTODO procesarLinea **
************************/  
    /*Método que convierte la cadena de texto Scanner para poder utilizar métodos
      Parámetro: recibe el texto que contiene la linea, PrintStream sobre el que se 
      quiere escribir y el objeto Scanner teclado */
    public static void procesarLinea(String texto, PrintStream escribir, Scanner teclado) {
      Scanner linea = new Scanner(texto);
      String total = "";
      while(linea.hasNext()) {
         String palabra = linea.next();
          // filtrar si la palabra empieza por < mostrar en pantalla
         if(palabra.startsWith("<") && palabra.endsWith(">")) {
            System.out.println(palabra.substring(1).replace(">",":").replace("-"," "));
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

}// fin class