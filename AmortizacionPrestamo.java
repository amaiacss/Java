//	Nombre: Amaia Casas Echevarria.
//	Fecha: 05/11/2021
//	Módulo: Programación.
//	UD2
//	PROG02 Tarea Evaluación	01.Realiza un programa en Java. 
//	Descripción: Realizar un programa que proporcionará diferentes cálculos	para depósitos a plazo fijo,utilizando variables,bucles	FOR
//	  y métodos con paso de parámetros y sentencia return, con el uso de las clases Math y Scanner.
//	Enlace YouTube a video autoevaluación: https://youtu.be/TnmQsCMlAtM

// Importar librería para el uso de Scanner
import java.util.*;

public class AmortizacionPrestamo {
	// CONSTANTES. 
	static final int PLAZO_MIN = 5;
	static final int PLAZO_MAX = 10;
	   
	static final double INTERES_MIN = 2;
	static final double INTERES_MAX = 5;

   // Método principal, se crea el objeto Scanner, imprime una introducción y llama a otros métodos para calcular los datos que se piden.
	public static void main(String[] args) {
   
		 // Crear el objeto teclado para la lectura de los datos por teclado
	    Scanner teclado = new Scanner(System.in);
	    
	    // Introducción
	    System.out.println("Este es un programa para calcular las cuotas de un préstamo");
	    System.out.println("Pedirá el capital del préstamo (euros), el interés anual a pagar (%) y su duración (años)");
	    System.out.println("Calculará para cada año, el capital pendiente y la cuota a pagar, intereses y amortización");
	    System.out.println("Empezamos ya\n");
	    
	    System.out.println("Introduce la cantidad solicitada para el préstamo:");      
	    double prestamo = teclado.nextDouble();
	    
	    calculoCuotas(prestamo);
	    
	    calculoPrestamo(teclado, prestamo);

	}
	   
/***********************
**MÉTODO calculoCuotas**
************************/
	   
   // Metodo que recibe un parametro double con la cantidad del préstamo y calcula las cuotas a pagar.
   // Plazos entre 5 y 10 años y los intereses desde 2% a 5% incrementándose un 0,5% cada vez.
	public static void calculoCuotas(double prestamo) {
		System.out.println("Estas son las cuotas a pagar para diferentes intereses y plazos");
		
		// imprime los años del 5 al 10
		for(int a = PLAZO_MIN; a <= PLAZO_MAX; a++) {
			System.out.print(a + " Años\t");
			
			// Realiza el calculo de las cuotas utilizando el método cuotas() y redondea a dos digitos, en cada vuelta suma 0.5.
			for(double i = INTERES_MIN; i <= INTERES_MAX; i += 0.5) {
				System.out.print(redondea(cuotas(i, a, prestamo)) + "(" + i + "%)\t");
			}
			System.out.println();
		}
	}

/****************
**MÉTODO cuotas**
****************/ 
	// Método que recibe como parámetros el interes, los años y la cantidad del prestamo para realizar el calculo de las cuotas.
	// Devuelve un valor double.
	public static double cuotas(double i, int a, double prestamo) {
		//Formula prestamo * i / 1 -(1 + i) elevado a -duracion.
		 double interes = i/100;
	    double dividendo = prestamo * interes;
	    double divisor = 1 - Math.pow((1 + interes), -a);
	    double resultado = dividendo / divisor;
	    return resultado;
	}
	
/******************
**MÉTODO redondea**
*******************/  
	// Método que recibe un parametro tipo double y lo redondea a dos dígitos.
	// Devuelve un valor double
	public static double redondea(double operacion) {
		double resultado = (double)Math.round(operacion*100)/100;
	   return resultado;
	}
	
/*************************
**MÉTODO calculoPrestamo**
**************************/  
	 /* El método pedirá el interes anual y el número de años y calculará el capital pendiente, la cuota anual,los intereses a pagar y el capital amortizado para cada año del prestamo*/
	// Recibe los parametros teclado y la cantidad del prestamo
	public static void calculoPrestamo(Scanner teclado, double prestamo) {
		
		// Solicita al usuario el interes anual del prestamo
		System.out.println("Introduce el interés anual que se aplicará al préstamo en %:");
		double i_anual = teclado.nextDouble();
		
		// Solicita al usuario el número de años.
		System.out.println("Introduce el número de años que va a durar el préstamo: ");
		int plazo = teclado.nextInt();
		
		//Con esta variable actualizaré el capital pendiente en cada año.
		double amortizacion = 0;
		// Calculo de la cuota anual, para cada año será la misma.
		double cuota_anual = cuotas(i_anual, plazo, prestamo);
		
		for(int a = 1; a <= plazo; a++) {
			// actualizo el capital pendiente
	         prestamo = prestamo - amortizacion;
	        // cálculo de los intereses
	         double intereses = prestamo * (i_anual/100);
	        // cálculo de la amortización del prestamo
	         amortizacion = cuota_anual - intereses;
         
			System.out.println("Año: " + a + "\n"
					+ "\tCapital Pendiente: " + redondea(prestamo) + "\n"
					+ "\tCuota Anual: "+ redondea(cuota_anual)+ "\n"
					+ "\tIntereses a pagar: " + redondea(intereses) + "\n"
					+ "\tAmortización: " + redondea(amortizacion));			
		}
	}
}
