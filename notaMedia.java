//Descripción: El programa permitirá calcular la nota media de un estudiante a partir de la calificación del examen final, 
//            el examen parcial y las tareas realizadas durante el curso, usando para ello,estructuras condicionales IF-ELSE, 
//            variables, bucles FOR y métodos con paso de parámetros y sentencia return así como, las funcionalidades de las
//             clases de las librerías de java.
import java.util.*;

public class notaMedia {
	
	public static final int MAX_CALIFICACION = 100;
	public static final int PUNTOS_PRESENCIALES = 5;
	public static final int MAX_PRESENCIALES = 30;
	
	// Método principal
	public static void main(String[] args) {
		// Objeto tipo Scanner para pedir datos por teclado.Se pasará como parámetro a los métodos que necesiten.
		Scanner teclado = new Scanner(System.in);
				
		// Configuro el Scanner para que el idioma sea el inglés
		// Los números double se deberán escribir con punto y no con coma
		teclado.useLocale(Locale.ENGLISH);      
		
		presentacion();
		
		double persona1 = calculoNota(teclado);
	}
	
/************************
* MÉTODO presentación  **
*************************/	   
// Muestra la descripción del programa.
	public static void presentacion() {
		System.out.println("Este programa lee las calificaciones de exámenes y tareas\n"
	                       + "y calcula la nota final del módulo para un estudiante.\n"
	                       + "También podría hacerlo para 2 estudiantes y comparar sus notas. Funcionaría sin problemas\n");
	}
	
/************************
** MÉTODO calculoNota  **
*************************/
	// Método que contiene la línea de título y otros métodos.
	// Parámetros : objeto Scanner
	// Return: tipo double, devuelve el total de la suma de las notas finales ponderadas.
	public static double calculoNota(Scanner teclado) {
				
		System.out.print("Parcial:\n");      
	    double ponderada1 = getNota(teclado);  
	          
	    System.out.print("Final:\n");      
	    double ponderada2 = getNota(teclado);
	      
	    System.out.print("Tareas:\n");      
	    double ponderada3 = tareas(teclado);
	      
	    double media = media(ponderada1, ponderada2, ponderada3);
	      
	    return media;
	}

/************************
***  MÉTODO getNota  ***
*************************/
	// Método que pide por teclado el peso y la calificación del examen,y si se ha obtenido puntos extra,
	// y calcula la nota final y la ponderada llamando a otros métodos.
	// Parámetros: Objeto de tipo Scanner para leer datos por teclado.
	public static double getNota(Scanner teclado) {
	   
		System.out.println("Introduce el peso del examen (0-100): ");
	    int peso = teclado.nextInt();
	         
	    System.out.println("Introduce la calificación del examen (0-100): ");
	    int calificacion = teclado.nextInt();
	         
	    System.out.println("¿Has obtenido puntos extra (1=Si, 2=No)? ");
	    int respuesta = teclado.nextInt();
	    int puntosExtra = puntosExtra(teclado,respuesta);
	         
	    int notaFinal = notaFinal(calificacion, puntosExtra);
	    System.out.println("Nota final = " + notaFinal + " / " + MAX_CALIFICACION);
	      
	    double finalPonderada = finalPonderada(notaFinal,peso);
	    System.out.printf("Nota final ponderada = %.1f / %d %n", finalPonderada, peso);
	      
	    return finalPonderada;
	}
	
/************************
***  MÉTODO puntosExtra ***
*************************/	   
	// Método que si hay puntos extra, los solicita y lo devuelve
	// Parámetros: objeto Scanner y valor int que indica si hay puntos extra o no.
	// Return: tipo int leído.
	public static int puntosExtra(Scanner teclado, int respuesta) {
		int puntoExtra = 0;
	    if (respuesta == 1){
	    	System.out.println("Introduce el total de puntos extra: ");
	        puntoExtra = teclado.nextInt();                            
	    } 
	    return puntoExtra;
	}
	      
/************************
***  MÉTODO notaFinal ***
*************************/
	// Método que calcula la nota final y la restringe a una nota máxima de 100.
	// Parámetros: dos valores de tipo int que han sido introducidos por teclado desde el método getNota.
	// Return: valor int calculado.
	public static int notaFinal(int calificacion, int puntosExtra) {	   
		int notaFinal =  calificacion + puntosExtra; 
	    if (notaFinal > MAX_CALIFICACION){
	    	notaFinal = MAX_CALIFICACION;
	    }
	    return notaFinal;	           
	}	   
	   
/************************
*  MÉTODO finalPonderada *
*************************/
	// Método que calcula la nota final ponderada.
	// Parámetros: dos valores de tipo int necesarios para el calculo de la nota ponderada.
	// Return: valor double calculado.     
	public static double finalPonderada(int notaFinal, int peso) {
		return (notaFinal * peso) / 100.0;
	}  
/************************
*****  MÉTODO tareas ****
*************************/
	// Método que solicita por teclado diferentes valores para realizar los calculos y obtener la nota ponderada.
	// Parametro: objeto Scanner
	// Return: tipo double con el calculo de la nota final ponderada de las tareas.
	public static double tareas(Scanner teclado) {
		System.out.println("Introduce el peso de las tareas (0-100): ");
	    int peso = teclado.nextInt();
	    
	    System.out.println("Introduce el número de tareas: ");
	    int numTareas = teclado.nextInt();
	         
	    int sumaPuntuacion = 0;
	    int sumaPuntuacionMax = 0;
	     
	    for(int i = 1; i <= numTareas; i++) {	      
	    	System.out.println("Tarea " + i + " : Introduce la puntuación obtenida y la puntuación máxima:  ");
	    	int puntuacion = teclado.nextInt();
	    	int puntuacionMax = teclado.nextInt();
	         
	    	sumaPuntuacion += puntuacion;
	    	sumaPuntuacionMax += puntuacionMax;
	    }
	               
	      System.out.println("Introduce el número de presenciales a las que has atendido: ");
	      int numPresenciales = teclado.nextInt();         
	      int notaPresencial = numPresenciales * PUNTOS_PRESENCIALES;
	      if(notaPresencial > MAX_PRESENCIALES) {
	    	  notaPresencial = MAX_PRESENCIALES;
	      }
	      System.out.println("Nota de las presenciales = " + notaPresencial + " / " + MAX_PRESENCIALES);
	         
	      int totalMax = sumaPuntuacionMax + MAX_PRESENCIALES;
	      int notaFinal = sumaPuntuacion + notaPresencial;
	      System.out.println("Nota final = " + notaFinal + " / " + totalMax);
	      
	      double notaPonderada = (double) (notaFinal * peso) / totalMax;
	      System.out.printf("Nota Final ponderada = %.1f / %d %n", notaPonderada, peso);
	          
	      return notaPonderada;	          
	} 
/**********************
****** MÉTODO media ***
***********************/ 
	// Método que calcula el total de la suma de las notas finales ponderadas, con este resultado y a través de un 'if', dependiendo
	//  de la puntuación se le asigna un valor y un mensaje que despúes se visualizará por pantalla,
	// Parametros: Recibe 3 parametros de tipo double, necesarios para calcular el total de la suma de las notas finales ponderadas.
	//Return: tipo double, devuelve el resultado de la total ponderada que será guardada en una variable para poder compararla con
	//    la media de otro alumn@
	public static double media (double ponderada1, double ponderada2, double ponderada3) {
	      
		double totalPonderada = ponderada1 + ponderada2 + ponderada3;
	    System.out.printf("La calificación total obtenida es: %.1f\n", totalPonderada );
	                           
	    String mensaje = "";
	    double valor = 0;
	    if (totalPonderada >= 85){
	    	valor = 3.0;
	        mensaje = "Excelente trabajo";
	    } else if (totalPonderada <= 84.99 && totalPonderada >= 75) {
	    	valor = 2.0;
	        mensaje = "Gran trabajo";         
	    } else if (totalPonderada <= 74.99 && totalPonderada >= 60) {
	    	valor = 0.7;
	        mensaje = "Todavía tienes trabajo por hacer";
	    } else {
	        valor = 0.0;
	        mensaje = "Otra vez será";
	    }
	         
	    System.out.println("La nota final en una escala del 0 al 4 es al menos: " + valor);         
	    System.out.print(mensaje);
	      
	    return totalPonderada;	      
	}
}

