//	Nombre: Amaia Casas Echevarria.
//	Fecha: 05/11/2021
//	M�dulo: Programaci�n.
//	UD2
//	PROG02 Tarea Evaluaci�n	01.Realiza un programa en Java. 
//	Descripci�n: Realizar un programa que proporcionar� diferentes c�lculos	para dep�sitos a plazo fijo,utilizando variables,bucles	FOR
//	  y m�todos con paso de par�metros y sentencia return, con el uso de las clases Math y Scanner.
//	Enlace YouTube a video autoevaluaci�n: https://youtu.be/TnmQsCMlAtM

// Importar librer�a para el uso de Scanner
import java.util.*;

public class AmortizacionPrestamo {
	// CONSTANTES. 
	static final int PLAZO_MIN = 5;
	static final int PLAZO_MAX = 10;
	   
	static final double INTERES_MIN = 2;
	static final double INTERES_MAX = 5;

   // M�todo principal, se crea el objeto Scanner, imprime una introducci�n y llama a otros m�todos para calcular los datos que se piden.
	public static void main(String[] args) {
   
		 // Crear el objeto teclado para la lectura de los datos por teclado
	    Scanner teclado = new Scanner(System.in);
	    
	    // Introducci�n
	    System.out.println("Este es un programa para calcular las cuotas de un pr�stamo");
	    System.out.println("Pedir� el capital del pr�stamo (euros), el inter�s anual a pagar (%) y su duraci�n (a�os)");
	    System.out.println("Calcular� para cada a�o, el capital pendiente y la cuota a pagar, intereses y amortizaci�n");
	    System.out.println("Empezamos ya\n");
	    
	    System.out.println("Introduce la cantidad solicitada para el pr�stamo:");      
	    double prestamo = teclado.nextDouble();
	    
	    calculoCuotas(prestamo);
	    
	    calculoPrestamo(teclado, prestamo);

	}
	   
/***********************
**M�TODO calculoCuotas**
************************/
	   
   // Metodo que recibe un parametro double con la cantidad del pr�stamo y calcula las cuotas a pagar.
   // Plazos entre 5 y 10 a�os y los intereses desde 2% a 5% increment�ndose un 0,5% cada vez.
	public static void calculoCuotas(double prestamo) {
		System.out.println("Estas son las cuotas a pagar para diferentes intereses y plazos");
		
		// imprime los a�os del 5 al 10
		for(int a = PLAZO_MIN; a <= PLAZO_MAX; a++) {
			System.out.print(a + " A�os\t");
			
			// Realiza el calculo de las cuotas utilizando el m�todo cuotas() y redondea a dos digitos, en cada vuelta suma 0.5.
			for(double i = INTERES_MIN; i <= INTERES_MAX; i += 0.5) {
				System.out.print(redondea(cuotas(i, a, prestamo)) + "(" + i + "%)\t");
			}
			System.out.println();
		}
	}

/****************
**M�TODO cuotas**
****************/ 
	// M�todo que recibe como par�metros el interes, los a�os y la cantidad del prestamo para realizar el calculo de las cuotas.
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
**M�TODO redondea**
*******************/  
	// M�todo que recibe un parametro tipo double y lo redondea a dos d�gitos.
	// Devuelve un valor double
	public static double redondea(double operacion) {
		double resultado = (double)Math.round(operacion*100)/100;
	   return resultado;
	}
	
/*************************
**M�TODO calculoPrestamo**
**************************/  
	 /* El m�todo pedir� el interes anual y el n�mero de a�os y calcular� el capital pendiente, la cuota anual,los intereses a pagar y el capital amortizado para cada a�o del prestamo*/
	// Recibe los parametros teclado y la cantidad del prestamo
	public static void calculoPrestamo(Scanner teclado, double prestamo) {
		
		// Solicita al usuario el interes anual del prestamo
		System.out.println("Introduce el inter�s anual que se aplicar� al pr�stamo en %:");
		double i_anual = teclado.nextDouble();
		
		// Solicita al usuario el n�mero de a�os.
		System.out.println("Introduce el n�mero de a�os que va a durar el pr�stamo: ");
		int plazo = teclado.nextInt();
		
		//Con esta variable actualizar� el capital pendiente en cada a�o.
		double amortizacion = 0;
		// Calculo de la cuota anual, para cada a�o ser� la misma.
		double cuota_anual = cuotas(i_anual, plazo, prestamo);
		
		for(int a = 1; a <= plazo; a++) {
			// actualizo el capital pendiente
	         prestamo = prestamo - amortizacion;
	        // c�lculo de los intereses
	         double intereses = prestamo * (i_anual/100);
	        // c�lculo de la amortizaci�n del prestamo
	         amortizacion = cuota_anual - intereses;
         
			System.out.println("A�o: " + a + "\n"
					+ "\tCapital Pendiente: " + redondea(prestamo) + "\n"
					+ "\tCuota Anual: "+ redondea(cuota_anual)+ "\n"
					+ "\tIntereses a pagar: " + redondea(intereses) + "\n"
					+ "\tAmortizaci�n: " + redondea(amortizacion));			
		}
	}
}
